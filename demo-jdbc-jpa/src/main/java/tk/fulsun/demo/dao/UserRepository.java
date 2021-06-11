package tk.fulsun.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tk.fulsun.demo.entity.User;

/**
 * @author fsun7
 * @description: 数据访问接口
 * @date 6/11/2021 2:28 PM
 */
public interface UserRepository extends JpaRepository<User, Long> {
  User findByName(String name);

  User findByNameAndAge(String name, Integer age);

  @Query("from User u where u.name=:name")
  User findUser(@Param("name") String name);
}
