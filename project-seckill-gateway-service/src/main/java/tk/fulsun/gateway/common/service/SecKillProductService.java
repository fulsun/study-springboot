package tk.fulsun.gateway.common.service;

import java.util.List;
import tk.fulsun.gateway.common.dao.dataobject.TSeckillProduct;

/**
 * @author fulsun
 * @description: 秒杀商品service接口
 * @date 6/15/2021 5:17 PM
 */
public interface SecKillProductService {
  /**
   * 获取秒杀商品列表
   *
   * @return
   */
  List<TSeckillProduct> queryTSeckillProductList();

  /**
   * 根据产品id查询产品信息
   *
   * @param prodId
   * @return
   */
  TSeckillProduct queryProdByProdId(String prodId);

  /**
   * 修改产品信息
   *
   * @param updateProdData
   * @return
   */
  void updateProdInfo(TSeckillProduct updateProdData);

  /**
   * 预减库存
   *
   * @param prodId
   * @return
   */
  boolean preReduceProdStock(String prodId);
}
