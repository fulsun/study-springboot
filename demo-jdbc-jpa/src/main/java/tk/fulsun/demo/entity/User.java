package tk.fulsun.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fulsun
 * @description: 用户实体类 create table user( name varchar(20), age int );
 * @date 6/11/2021 2:25 PM
 */
@Entity
@Data
@NoArgsConstructor
public class User {

  @Id @GeneratedValue private Long id;

  private String name;
  private Integer age;

  public User(String name, Integer age) {
    this.name = name;
    this.age = age;
  }
}
