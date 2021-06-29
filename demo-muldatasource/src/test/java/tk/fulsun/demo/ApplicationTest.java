package tk.fulsun.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author fulsun
 * @description: 主测试类
 * @date 6/11/2021 3:25 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
    @Before
    public void before() {
        System.out.println("start=============");
    }

    @Test
    public void testStartUp() {
        //doSmthing();
        System.out.println("--------------测试----------");
    }
}
