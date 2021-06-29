package tk.fulsun.demo.mybatis.mapper.s;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fulsun
 * @description: UserSecondary
 * @date 6/11/2021 5:22 PM
 */
@Data
@NoArgsConstructor
public class UserSecondary {
  private Long id;

  private String name;
  private Integer age;

  public UserSecondary(String name, Integer age) {
    this.name = name;
    this.age = age;
  }
}
