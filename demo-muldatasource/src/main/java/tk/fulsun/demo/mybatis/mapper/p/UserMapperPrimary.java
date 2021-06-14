package tk.fulsun.demo.mybatis.mapper.p;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author fsun7
 * @description: UserMapperPrimary
 * @date 6/11/2021 5:22 PM
 */
public interface UserMapperPrimary {
  @Select("SELECT * FROM USER WHERE NAME = #{name}")
  UserPrimary findByName(@Param("name") String name);

  @Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name}, #{age})")
  int insert(@Param("name") String name, @Param("age") Integer age);

  @Delete("DELETE FROM USER")
  int deleteAll();
}
