package tk.fulsun.demo.mybatis.mapper.p;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fulsun
 * @description: UserPrimary
 * @date 6/11/2021 5:21 PM
 */
@Data
@NoArgsConstructor
public class UserPrimary {
  private Long id;

  private String name;
  private Integer age;

  public UserPrimary(String name, Integer age) {
    this.name = name;
    this.age = age;
  }
}
