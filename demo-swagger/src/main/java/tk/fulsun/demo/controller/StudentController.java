package tk.fulsun.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.fulsun.demo.entity.User;

/**
 * @author fsun7
 * @description: 学生管理
 * @date 6/10/2021 4:09 PM
 */
@Api(tags = {"3-学生管理"})
@RestController
@RequestMapping(value = "/student")
public class StudentController {

  @ApiOperation(value = "获取学生清单", tags = "1-教学管理")
  @GetMapping("/list")
  public String bbb() {
    return "bbb";
  }

  @ApiOperation("获取教某个学生的老师清单")
  @GetMapping("/his-teachers")
  public String ccc() {
    return "ccc";
  }

  @ApiOperation("创建一个学生")
  @PostMapping("/aaa")
  public String aaa(@RequestBody User user) {
    return "aaa";
  }
}
