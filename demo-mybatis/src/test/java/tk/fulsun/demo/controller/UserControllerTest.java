package tk.fulsun.demo.controller;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


/**
 * <pre>
 *   • MockMvc允许我们方便的发送 HTTP 请求。
 *   • SpringBootTest方便的创建一个 Spring Boot 项目的测试程序。
 * mockMvc.perform执行一个请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理，返回一个ResultActions实例
 * MockMvcRequestBuilders.get("/student/findAll") 根据uri模板和uri变量值 构造一个GET,PUT,POST,DELETE等请求，Post请求就用.post方法
 * contentType(MediaType.APPLICATION_JSON_UTF8)代表发送端发送的数据格式是application/json;charset=UTF-8
 * accept(MediaType.APPLICATION_JSON_UTF8)代表客户端希望接受的数据类型为application/json;charset=UTF-8
 * ResultActions.andExpect添加执行完成后的断言
 * ResultActions.andExpect(MockMvcResultMatchers.status().isOk())方法看请求的状态响应码是否为200如果不是则抛异常，测试不通过
 * ResultActions.andExpect(MockMvcResultMatchers.jsonPath(“$.author”).value("username"))这里jsonPath用来获取author字段比对是否为嘟嘟MD独立博客,不是就测试不通过
 * ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情，比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息
 * </pre>
 *
 * @author fsun7
 * @title: UserControllerTest
 * @projectName springboot-study
 * @description: 接口测试
 * @date 5/26/2021 4:19 PM
 */
// @AutoConfigureMockMvc
//引入Spring对JUnit4的支持。
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = demo.Application.class)
//开启Web应用的配置，用于模拟ServletContext。
@WebAppConfiguration
class UserControllerTest {

  // @Autowired
  private MockMvc mvc;

  //·@Before：JUnit中定义在测试用例@Test内容执行前预加载的内容，这里用来初始化对HelloController的模拟。
  @Before
  public void setUp() throws Exception {
    mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
  }

  @Test
  public void contextLoads() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/user/all").accept(MediaType.APPLICATION_JSON_UTF8))
        // 添加断言
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.equalTo("Hello World")))
        // 添加返回结果 一般在测试时候用
        .andDo(MockMvcResultHandlers.print());

    // String file = "test.xlsx";
    // // 执行一个post请求
    // mvc.perform(MockMvcRequestBuilders.post("/student/uploadFile")
    //     // post请求的内容
    //     .content(file)
    //     // post请求数据类型
    //     .contentType(MediaType.APPLICATION_JSON_VALUE))
    //     // 添加断言
    //     .andExpect(MockMvcResultMatchers.status().isOk())
    //     // 添加返回结果
    //     .andDo(MockMvcResultHandlers.print());
  }
}