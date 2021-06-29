package tk.fulsun.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import tk.fulsun.demo.mapper.UserMapper;
import tk.fulsun.demo.model.User;
import tk.fulsun.demo.model.UserExample;
import tk.fulsun.demo.model.UserExample.Criteria;

import java.util.List;

/**
 * @author fulsun
 * @description: 用户管理的服务层
 * @date 5/31/2021 3:32 PM
 */
@Service
public class UserService {

  @Autowired private UserMapper userMapper;

  @GetMapping("/user/all")
  public List<User> getAllUser() {
    return userMapper.selectByExample(null);
  }

  // 查询,使用缓存 查询结果为空则不缓存
//  @Cacheable(cacheNames = "users", key = "#name", unless = "#result==null")
  @Cacheable("users")
  public User getUserByName(@PathVariable("name") String name) {
    UserExample example = new UserExample();
    Criteria criteria = example.createCriteria();
    criteria.andNameEqualTo(name.trim());
    List<User> users = userMapper.selectByExample(example);
    if (users.size() > 1) {
      return null;
    } else {
      return users.get(0);
    }
  }

  @Cacheable(cacheNames = "users", key = "#name", unless = "#result.size()==0")
  public List<User> getUserlikeName(@PathVariable("name") String name) {
    UserExample example = new UserExample();
    Criteria criteria = example.createCriteria();
    criteria.andNameLike('%' + name + '%');
    return userMapper.selectByExample(example);
  }

  // 查询,使用缓存 查询结果为空则不缓存
  @Cacheable(cacheNames = "users", key = "#id", unless = "#result==null")
  public User getUserById(@PathVariable("id") int id) {
    return userMapper.selectByPrimaryKey(id);
  }

  public int addUser(@RequestBody User user) {
    return userMapper.insertSelective(user);
  }

  // 更新,修改缓存 key = "#root.args[0].id"
  //  @CachePut(cacheNames = "users", key = "#result.id")
  @Caching(
      put = {@CachePut(cacheNames = "users", key = "#result.id")
        // 开启后会覆盖格式为User类型，再次读取会转换为list类型，出现ClassCastException
        // @CachePut(cacheNames = "users", key = "#result.name"
      })
  public User updateUser(@RequestBody User user) {
    userMapper.updateByPrimaryKeySelective(user);
    return userMapper.selectByPrimaryKey(user.getId());
  }

  // 删除,删除缓存
  // beforeInvocation=true,防止执行过程中出现异常,不管这个方法执行成功与否，该缓存都将不存在。
  @CacheEvict(beforeInvocation = true, cacheNames = "users", key = "#id")
  public int delUser(@PathVariable("id") int id) {
    return userMapper.deleteByPrimaryKey(id);
  }
}
