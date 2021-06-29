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
import tk.fulsun.demo.mapper.UserMapper;
import tk.fulsun.demo.model.User;
import tk.fulsun.demo.model.UserExample;
import tk.fulsun.demo.model.UserExample.Criteria;

/**
 * @author fulsun
 * @title: TestController
 * @projectName springboot-study
 * @description: 用户控制层代码
 * @date 5/26/2021 1:57 PM
 */
@RestController
public class UserController {

  @Autowired private UserMapper userMapper;

  @GetMapping("/user/all")
  public List<User> getAllUser() {
    return userMapper.selectByExample(null);
  }

  @GetMapping("/user/{name}")
  public List<User> getUserById(@PathVariable("name") String name) {

    UserExample example = new UserExample();
    Criteria criteria = example.createCriteria();
    criteria.andNameLike('%' + name + '%');
    return userMapper.selectByExample(example);
  }

  @PostMapping("/user/add")
  public String addUser(@RequestBody User user) {
    int status = userMapper.insertSelective(user);
    return status > 0 ? "成功" : "失败";
  }

  @PutMapping("/user/update")
  public String updateUser(@RequestBody User user) {
    int status = userMapper.updateByPrimaryKeySelective(user);
    return status > 0 ? "成功" : "失败";
  }

  @DeleteMapping("/user/{id}")
  public String delUser(@PathVariable("id") int id) {
    int status = userMapper.deleteByPrimaryKey(id);
    return status > 0 ? "成功" : "失败";
  }
}
