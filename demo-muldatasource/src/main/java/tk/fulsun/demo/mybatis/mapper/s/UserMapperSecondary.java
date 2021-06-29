package tk.fulsun.demo.mybatis.mapper.s;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author fulsun
 * @description: UserMapperSecondary
 * @date 6/11/2021 5:23 PM
 */
public interface UserMapperSecondary {
  @Select("SELECT * FROM USER WHERE NAME = #{name}")
  UserSecondary findByName(@Param("name") String name);

  @Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name}, #{age})")
  int insert(@Param("name") String name, @Param("age") Integer age);

  @Delete("DELETE FROM USER")
  int deleteAll();
}
