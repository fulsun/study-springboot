package tk.fulsun.demo.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * <p> 接收请求参数
 *
 * @author fulsun
 * @date 2021/7/1
 */
@Data
public class UserDto {
    private String name;
    @Min(value = 0, message = "年龄应该在0-100之间")
    @Max(100)
    private Integer age;

}
