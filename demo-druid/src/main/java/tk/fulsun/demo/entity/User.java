package tk.fulsun.demo.entity;

/**
 * @author fulsun
 * @title: User
 * @projectName springboot-study
 * @description: 用户实体类
 * @date 5/26/2021 1:53 PM
 */
public class User {

  private Integer id;
  private String name;
  private Integer age;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }
}
