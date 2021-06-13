package tk.fulsun.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.fulsun.demo.mapper.UserMapper;
import tk.fulsun.demo.model.User;
import tk.fulsun.demo.model.UserExample;

import java.util.List;

/**
 * @Description ControllerA
 * @Date 2021/6/13
 * @Created by 凉月-文
 */
@RestController
@RequestMapping("/test")
public class ControllerA {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("")
    public String test() {
        return "test-ControllerA";
    }

    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    public List<User> getAllUser() {
        return userMapper.selectByExample(null);
    }

    @GetMapping("/user/{name}")
    public List<User> getUserById(@PathVariable("name") String name) {

        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
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
