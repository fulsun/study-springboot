package tk.fulsun.demo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author fulsun
 * @description: FastApplication
 * @date 6/9/2021 12:25 PM
 */
@SpringBootApplication
public class FastApplication {
  public static void main(String[] args) {
    SpringApplication.run(FastApplication.class, args);
  }

  public static String obj2json(Object obj) {
    String json = "";
    try {
      json =
          JSONObject.toJSONString(
              obj,
              // collection 空值输出 []
              SerializerFeature.WriteMapNullValue,
              // 字符串类型空值输出空字符串""
              SerializerFeature.WriteNullStringAsEmpty,
              // 数值类型的空值输出为0
              SerializerFeature.WriteNullNumberAsZero,
              // 布尔类型为null输出false
              SerializerFeature.WriteNullBooleanAsFalse,
              SerializerFeature.DisableCircularReferenceDetect);
    } catch (Exception e) {
      e.printStackTrace();
      return "";
    }
    return json;
  }
}
