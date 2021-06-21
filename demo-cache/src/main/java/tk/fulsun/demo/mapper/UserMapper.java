package tk.fulsun.demo.mapper;

import org.apache.ibatis.annotations.Param;
import tk.fulsun.demo.model.User;
import tk.fulsun.demo.model.UserExample;

import java.util.List;

public interface UserMapper {
  long countByExample(UserExample example);

  int deleteByExample(UserExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(User record);

  int insertSelective(User record);

  List<User> selectByExample(UserExample example);

  User selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

  int updateByExample(@Param("record") User record, @Param("example") UserExample example);

  int updateByPrimaryKeySelective(User record);

  int updateByPrimaryKey(User record);
}
