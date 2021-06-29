package tk.fulsun.order.service;

import java.util.List;
import tk.fulsun.order.dao.dataobject.TSeckillProduct;

/**
 * @author fulsun
 * @description: 秒杀商品service接口
 * @date 6/16/2021 4:15 PM
 */
public interface SecKillProductService {

  /**
   * 获取秒杀商品列表
   *
   * @return
   */
  List<TSeckillProduct> querySecKillProductList();

  /**
   * 查询秒杀商品信息
   *
   * @param prodId
   * @return
   */
  TSeckillProduct querySecKillProductByProdId(String prodId);

  /**
   * 商品减库存
   *
   * @param prodId
   * @return
   */
  boolean decreaseProdStock(String prodId);
}
