package tk.fulsun.demo.service;



import tk.fulsun.demo.entity.User;

import java.util.List;

/**
 * 通用接口
 *
 */
public interface IService {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

}
