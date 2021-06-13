package tk.fulsun.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.fulsun.demo.mapper.UserMapper;
import tk.fulsun.demo.model.User;

/**
 * @Description ServiceA
 * @Date 2021/6/13
 * @Created by 凉月-文
 */
@Service
public class ServiceA {
    public void doSomething() {
        System.out.println("ServiceA running .... ");
    }

    @Autowired
    private UserMapper userDao;

    public User getUserById(Integer id) {
        return userDao.selectByPrimaryKey(id);
    }

    public Integer insertUser(User user) {
        return userDao.insert(user);
    }
}
