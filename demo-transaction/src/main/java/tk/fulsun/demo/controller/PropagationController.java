package tk.fulsun.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.fulsun.demo.service.impl.UserService2;

/**
 * @author fsun7
 * @description: 传播行为测试
 * @date 6/18/2021 1:39 PM
 */
@RestController
public class PropagationController {
  @Autowired private UserService2 userService2;

  /** REQUIRED，当前存在事务，则加入该事务 事务嵌套为一个事务，结果为回滚成功 */
  @GetMapping("test1")
  public void insertBatchTest() {
    userService2.inserBatch();
  }

  /**
   * REQUIRES_NEW，当重新创建一个新的事务，如果当前存在事务，延缓当前的事务。
   * 这个延缓，或者说挂起，可能理解起来比较难  inserBatch拥有事务，然后后面循环调用的insert方法也有自己的事务
   * 根据定义，inserBatch的事务会被延缓。
   * 具体表现就是：后面的10次循环的事务在每次循环结束之后都会提交自己的事务，而inserBatch的事务，要等循环方法走完之后再提交。
   * 但由于第10次循环会抛出异常，则inserBatch的事务会回滚，既数据库中不会存在：（"service1", 11）的记录：
   */
  @GetMapping("test2")
  public void insertBatchTest2() {
    userService2.inserBatch2();
  }
}
