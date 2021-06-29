package tk.fulsun.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tk.fulsun.demo.model.User;
import tk.fulsun.demo.service.UserService;

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

  public User getUserByName(@PathVariable("name") String name) {
    return userService.getUserByName(name);
  }

  @GetMapping("/user/{name}")
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
