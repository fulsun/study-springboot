package tk.fulsun.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


/**
 * <pre>
 *   • MockMvc允许我们方便的发送 HTTP 请求。
 *   • SpringBootTest方便的创建一个 Spring Boot 项目的测试程序。
 * mockMvc.perform执行一个请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理，返回一个ResultActions实例
 * MockMvcRequestBuilders.get("/student/findAll") 根据uri模板和uri变量值 构造一个GET,PUT,POST,DELETE等请求，Post请求就用.post方法
 * contentType(MediaType.APPLICATION_JSON_UTF8)代表发送端发送的数据格式是application/json;charset=UTF-8
 * accept(MediaType.APPLICATION_JSON_UTF8)代表客户端希望接受的数据类型为application/json;charset=UTF-8
 * ResultActions.andExpect添加执行完成后的断言
 * ResultActions.andExpect(MockMvcResultMatchers.status().isOk())方法看请求的状态响应码是否为200如果不是则抛异常，测试不通过
 * ResultActions.andExpect(MockMvcResultMatchers.jsonPath(“$.author”).value("username"))这里jsonPath用来获取author字段比对是否为嘟嘟MD独立博客,不是就测试不通过
 * ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情，比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息
 * </pre>
 *
 * @author fulsun
 * @title: UserControllerTest
 * @projectName springboot-study
 * @description: 接口测试
 * @date 5/26/2021 4:19 PM
 */
//SpringBoot1.4版本之前用的是SpringJUnit4ClassRunner.class
@RunWith(SpringRunner.class)
//SpringBoot1.4版本之前用的是@SpringApplicationConfiguration(classes = Application.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
// 配置中关闭druid的filter：解决在mock环境下的filter空指针问题
@ActiveProfiles("dev")
class UserControllerTest1 {

  @Autowired
  private MockMvc mvc;


  @Test
  public void contextLoads() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/user/all").accept(MediaType.APPLICATION_JSON))
        // 添加断言
        .andExpect(MockMvcResultMatchers.status().isOk())
        // .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.equalTo("Hello World")));
        // 添加返回结果 一般在测试时候用
        .andDo(MockMvcResultHandlers.print());
  }
}