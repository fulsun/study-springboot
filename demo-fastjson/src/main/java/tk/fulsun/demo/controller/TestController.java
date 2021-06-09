package tk.fulsun.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fsun7
 * @description: 测试代码
 * @date 6/9/2021 12:20 PM
 */
@RestController
public class TestController {

  /**
   * 测试json结果
   *
   * <pre>
   * {
   * 	"skills":[
   * 		"java",
   * 		"SpringBoot",
   * 		"Hadoop"
   * 	],
   * 	"name":"fl6145",
   * 	"email":"fl_6145@163.com"
   * }
   * </pre>
   *
   * @return
   */
  @GetMapping("/fastjson")
  public HashMap<String, Object> json() {
    List<String> skills = new ArrayList<>(3);
    skills.add("java");
    skills.add("SpringBoot");
    skills.add("Hadoop");

    HashMap<String, Object> map = new HashMap<>();
    map.put("name", "fl6145");
    map.put("email", "fl_6145@163.com");
    map.put("skills", skills);
    return map;
  }
}
