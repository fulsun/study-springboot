package tk.fulsun.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tk.fulsun.demo.dto.UserDto;
import tk.fulsun.demo.exception.constant.enums.ArgumentResponseEnum;
import tk.fulsun.demo.exception.constant.enums.BusinessResponseEnum;
import tk.fulsun.demo.exception.pojo.response.BaseResponse;
import tk.fulsun.demo.exception.pojo.response.CommonResponse;
import tk.fulsun.demo.mapper.UserMapper;
import tk.fulsun.demo.model.User;
import tk.fulsun.demo.model.UserExample;

import java.util.List;

/**
 * @author fulsun
 * @title: TestController
 * @projectName springboot-study
 * @description: 用户控制层代码
 * @date 5/26/2021 1:57 PM
 */
@RestController
@Validated
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/user/all")
    public List<User> getAllUser() {
        return userMapper.selectByExample(null);
    }

    @GetMapping("/user/list")
    @Validated
    public BaseResponse getUser(@Validated UserDto user) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria1 = example.createCriteria();
        UserExample.Criteria criteria2 = example.createCriteria();
        if (!StringUtils.isEmpty(user.getName())) {
            criteria1.andNameLike("%" + user.getName() + "%");
        }
        if (null != user.getAge()) {
            criteria1.andAgeEqualTo(user.getAge());
        }

        example.getOredCriteria().add(criteria1);
        example.getOredCriteria().add(criteria2);

        List<User> lists = userMapper.selectByExample(example);
        return new CommonResponse<>(lists);
    }

    @GetMapping("/user/info/{id}")

    public BaseResponse getUserById(@PathVariable(value = "id") int id) {
        ArgumentResponseEnum.VALID_ERROR.assertNotNull(id);
        User user = userMapper.selectByPrimaryKey(id);
        BusinessResponseEnum.USER_NOT_FOUND.assertNotNull(user);
        return new CommonResponse<>(user);
    }

    @PostMapping("/user/add")
    public BaseResponse addUser(@Validated @RequestBody User user) {
        int status = userMapper.insertSelective(user);
        BusinessResponseEnum.OPERATION_FAIL.assertIsTrue(status > 0);
        return new CommonResponse(user);
    }

    @PutMapping("/user/update")
    public BaseResponse updateUser(@RequestBody User user) {
        int status = userMapper.updateByPrimaryKeySelective(user);
        BusinessResponseEnum.OPERATION_FAIL.assertIsTrue(status > 0);
        return new CommonResponse<>(user);
    }

    @DeleteMapping("/user/{id}")
    public BaseResponse delUser(@PathVariable("id") int id) {
        int status = userMapper.deleteByPrimaryKey(id);
        BusinessResponseEnum.OPERATION_FAIL.assertIsTrue(status > 0);
        return new BaseResponse(BusinessResponseEnum.OPERATION_SUCCESS);
    }
}
