package tk.fulsun.gateway.common.service.impl;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tk.fulsun.gateway.common.dao.dataobject.TSeckillProduct;
import tk.fulsun.gateway.common.service.SecKillProductService;

/**
 * @author fulsun
 * @description: TODO
 * @date 6/16/2021 10:01 AM
 */
@SpringBootTest
class SecKillProductServiceImplTest {
  @Autowired private SecKillProductService secKillProductService;

  @Test
  void queryTSeckillProductList() {
    List<TSeckillProduct> tSeckillProducts = secKillProductService.queryTSeckillProductList();
    for (TSeckillProduct tSeckillProduct : tSeckillProducts) {
      System.out.println(tSeckillProduct);
    }
  }

  @Test
  void queryProdByProdId() {
    TSeckillProduct pid_0001 = secKillProductService.queryProdByProdId("pid_0001");
    System.out.println(pid_0001);
  }

  @Test
  void updateProdInfo() {
    TSeckillProduct pid_0001 = secKillProductService.queryProdByProdId("pid_0001");
    pid_0001.setProdStock(666);
    secKillProductService.updateProdInfo(pid_0001);
  }
}
