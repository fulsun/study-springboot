package tk.fulsun.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author fulsun
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
   * 	"name":"fulsun",
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
    map.put("name", "fulsun");
    map.put("email", "fl_6145@163.com");
    map.put("skills", skills);
    return map;
  }
}
