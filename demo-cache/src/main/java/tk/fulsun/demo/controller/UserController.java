package tk.fulsun.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.fulsun.demo.model.User;
import tk.fulsun.demo.service.UserService;

import java.util.List;

/**
 * @author fulsun
 * @title: TestController
 * @projectName springboot-study
 * @description: 用户控制层代码
 * @date 5/26/2021 1:57 PM
 */
@RestController
public class UserController {

  @Autowired private UserService userService;

  @GetMapping("/user/all")
  public List<User> getAllUser() {
    return userService.getAllUser();
  }

  @GetMapping("/user/{name}")
  public User getUserByName(@PathVariable("name") String name) {
    return userService.getUserByName(name);
  }

  @GetMapping("/userlike/{name}")
  public List<User> getUserlikeName(@PathVariable("name") String name) {
    return userService.getUserlikeName(name);
  }

  @GetMapping("/users/{id}")
  public User getUserById(@PathVariable("id") int id) {
    return userService.getUserById(id);
  }

  @PostMapping("/user/add")
  public String addUser(@RequestBody User user) {
    int status = userService.addUser(user);
    return status > 0 ? "成功" : "失败";
  }

  @PutMapping("/user/update")
  public User updateUser(@RequestBody User user) {
    return userService.updateUser(user);
  }

  @DeleteMapping("/user/{id}")
  public String delUser(@PathVariable("id") int id) {
    int status = userService.delUser(id);
    return status > 0 ? "成功" : "失败";
  }
}
