package tk.fulsun.gateway.common.dao;

import java.util.List;
import tk.fulsun.gateway.common.dao.dataobject.secKillProduct;

/**
 * @author fsun7
 * @description: 秒杀商品Mapper接口
 * @date 6/15/2021 5:10 PM
 */
public interface SecKillProductMapper {
  /**
   * 获取秒杀商品列表
   *
   * @return
   */
  List<secKillProduct> querySecKillProductList();

  /**
   * 根据产品id查询产品
   *
   * @param prodId
   * @return
   */
  secKillProduct queryProdById(String prodId);

  /**
   * 更新商品信息
   *
   * @param updateProdData
   * @return
   */
  int updateProdInfo(secKillProduct updateProdData);
}
