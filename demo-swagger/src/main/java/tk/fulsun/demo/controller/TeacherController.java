package tk.fulsun.demo.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fsun7
 * @description: 教师管理
 * @date 6/10/2021 4:08 PM
 */
@Api(tags = {"2-教师管理", "1-教学管理"})
@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {

  @GetMapping("/xxx")
  public String xxx() {
    return "xxx";
  }
}
