package tk.fulsun.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fsun7
 * @description: 用户实体类
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
