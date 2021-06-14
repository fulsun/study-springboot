package tk.fulsun.demo.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.fulsun.demo.config.DataSourceNames;
import tk.fulsun.demo.databases.annotation.DataSource;
import tk.fulsun.demo.entity.User;
import tk.fulsun.demo.mapper.UserMapper;
import tk.fulsun.demo.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl extends BaseService<User> implements UserService {
    @Autowired
    private UserMapper mapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return mapper.insert(record);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return mapper.updateByPrimaryKey(record);
    }
}
