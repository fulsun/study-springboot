package tk.fulsun.demo.controller;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import tk.fulsun.demo.model.User;

/**
 * @Description ControllerATest
 * @Date 2021/6/13
 * @Created by 凉月-文
 */
//@ExtendWith(SpringExtension.class)
@SpringBootTest
class ControllerATest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;
    private MockHttpSession session;

    @BeforeEach
    public void setupMockMvc() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
        session = new MockHttpSession();
        User user = new User("root", 666);
        session.setAttribute("user", user); //拦截器那边会判断用户是否登录，所以这里注入一个用户
    }


    @Test
    void getAllUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/test/user/all")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getUserById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/test/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("张三"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(23))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Transactional
    void addUser() throws Exception {
        User user = new User("凉月文", 23);
        String json = JSON.toJSONString(user);
        mvc.perform(MockMvcRequestBuilders.post("/test/user/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes())
                .session(session))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void updateUser() throws Exception {
        User user = new User("凉月文", 22);
        user.setId(3);
        String json = JSON.toJSONString(user);
        mvc.perform(MockMvcRequestBuilders.put("/test/user/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes())
                .session(session))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void delUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/test/user/3")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}