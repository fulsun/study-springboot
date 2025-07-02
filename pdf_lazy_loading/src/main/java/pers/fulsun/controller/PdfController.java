package pers.fulsun.controller;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import freemarker.template.Configuration;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PdfController {
    // 设置默认值
    @Value("${pdf.file.path")
    private String pdfFilePath;


    @RequestMapping("/")
    public String hello() {
        System.out.println("OK"+pdfFilePath);
        File file = new File(pdfFilePath);
        System.out.println(file.length());
        return "index";
    }

    @RequestMapping("/pdfpreview")
    public void showPdf(HttpServletResponse response) throws IOException {
        File file = new File(pdfFilePath);
        // 校验文件是否存在
        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "PDF文件不存在");
            return;
        }

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=\"" + encodeFileName(file.getName()) + "\"");
        response.setContentLength((int) file.length());

        // 流式传输文件内容
        try (FileInputStream input = new FileInputStream(file)) {
            ServletOutputStream output = response.getOutputStream();
            IOUtils.copy(input, output);
        } catch (IOException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "文件处理失败");
            // 记录日志 : log.error("PDF文件处理失败", e);
            // e.printStackTrace();
        }
    }

    // 处理文件名，防止中文乱码
    private String encodeFileName(String fileName) throws UnsupportedEncodingException {
        return URLEncoder.encode(fileName, StandardCharsets.UTF_8.name()).replaceAll("\\+", "%20");
    }

    @CrossOrigin
    @RequestMapping("/pdfpreview2")
    public void showPdf(HttpServletRequest request, HttpServletResponse response) throws IOException {
        File file = new File(pdfFilePath);

        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "PDF文件不存在");
            return;
        }

        long fileLength = file.length();
        String rangeHeader = request.getHeader("Range");

        // 设置通用响应头
        response.setContentType("application/pdf");
        response.setHeader("Accept-Ranges", "bytes");

        if (rangeHeader == null) {
            // 首次访问，只返回文件元数据
            response.setHeader("Content-Disposition", "inline; filename=\"" + encodeFileName(file.getName()) + "\"");
            response.setContentLength((int) fileLength); // 不返回文件内容
        } else {
            // 解析 Range 请求头
            String[] ranges = rangeHeader.replace("bytes=", "").split("-");
            long start = Long.parseLong(ranges[0]);
            long end = ranges.length > 1 ? Long.parseLong(ranges[1]) : fileLength - 1;

            // 校验范围是否合法
            if (start < 0 || end >= fileLength || start > end) {
                response.setStatus(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
                response.setHeader("Content-Range", "bytes */" + fileLength);
                return;
            }

            // 设置响应头
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
            response.setHeader("Content-Range", "bytes " + start + "-" + end + "/" + fileLength);
            response.setContentLength((int) (end - start + 1));

            // 使用 RandomAccessFile 读取指定范围
            try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
                 OutputStream output = response.getOutputStream()) {
                randomAccessFile.seek(start);
                byte[] buffer = new byte[1024];
                long remaining = end - start + 1;
                int bytesRead;

                while (remaining > 0 && (bytesRead = randomAccessFile.read(buffer, 0, (int) Math.min(buffer.length, remaining))) != -1) {
                    output.write(buffer, 0, bytesRead);
                    remaining -= bytesRead;
                }
            }
        }
    }

}
