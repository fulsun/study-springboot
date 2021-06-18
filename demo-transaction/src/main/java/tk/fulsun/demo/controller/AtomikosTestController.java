package tk.fulsun.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.fulsun.demo.entity.User;
import tk.fulsun.demo.service.impl.AtomikosTestService;

/**
 * @author fsun7
 * @description: TODO
 * @date 6/18/2021 4:12 PM
 */
@RestController
public class AtomikosTestController {
  @Autowired private AtomikosTestService atomikosTestService;

  @GetMapping("atomikos")
  public void testTx1() {
    atomikosTestService.tx();
  }

  @GetMapping("atomikos2")
  public void testTx2() {
    atomikosTestService.tx2();
  }

  @GetMapping("primary")
  public List<User> getByNameByPrimary(String name) {
    return atomikosTestService.getByNameByPrimary(name);
  }

  @GetMapping("secondary")
  public List<User> getByNameBySecondary(String name) {
    return atomikosTestService.getByNameBySecondary(name);
  }
}
