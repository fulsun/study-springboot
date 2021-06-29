package tk.fulsun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.fulsun.annotation.AccessLimit;

/**
 * @author fulsun
 * @description: API接口
 * @date 6/9/2021 3:19 PM
 */
@RestController
public class FangshuaController {

  @GetMapping("/h1")
  public String hello() {
    return "hello world";
  }

  @AccessLimit(seconds = 5, maxCount = 10, needLogin = true)
  @RequestMapping("/h2")
  public String hello2() {
    return "请求成功";
  }
}
