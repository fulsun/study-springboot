package tk.fulsun.demo.service;

import java.util.List;
import tk.fulsun.demo.entity.User;

/**
 * @author fulsun
 * @description: 用户操作接口
 * @date 6/18/2021 10:22 AM
 */
public interface UserService {

  /**
   * 新增一个用户
   *
   * @param name
   * @param age
   */
  int create(String name, Integer age);

  /**
   * 根据name查询用户
   *
   * @param name
   * @return
   */
  List<User> getByName(String name);

  /**
   * 根据name删除用户
   *
   * @param name
   */
  int deleteByName(String name);

  /** 获取用户总量 */
  int getAllUsers();

  /** 删除所有用户 */
  int deleteAllUsers();
}
