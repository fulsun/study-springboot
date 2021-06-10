package tk.fulsun.demo.entity;

import com.sun.istack.internal.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * @author fsun7
 * @description: 用户实体类
 * @date 6/10/2021 5:31 PM
 */
@Data
@ApiModel(description = "用户实体")
public class User {

  @ApiModelProperty(value = "用户编号", position = 1)
  private Long id;

  @NotNull
  @Size(min = 2, max = 5)
  @ApiModelProperty(value = "用户姓名", position = 2)
  private String name;

  @NotNull
  @Max(100)
  @Min(10)
  @ApiModelProperty(value = "用户年龄", position = 3)
  private Integer age;

  @NotNull
  @Email
  @ApiModelProperty(value = "用户邮箱", position = 4)
  private String email;
}
