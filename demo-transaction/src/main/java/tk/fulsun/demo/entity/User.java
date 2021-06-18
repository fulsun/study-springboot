package tk.fulsun.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fsun7
 * @description: User实体
 * @date 6/18/2021 10:21 AM
 */
@Data
@NoArgsConstructor
public class User {
  private Long id;
  private String name;
  private Integer age;
}
