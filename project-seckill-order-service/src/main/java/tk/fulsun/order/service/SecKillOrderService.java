package tk.fulsun.order.service;

import tk.fulsun.order.dao.dataobject.TSeckillOrder;
import tk.fulsun.order.dto.Result;

/**
 * @author fulsun
 * @description: 秒杀订单本地service接口
 * @date 6/16/2021 3:35 PM
 */
public interface SecKillOrderService {
  /**
   * 根据订单号查询订单明细
   *
   * @param orderId
   * @return
   */
  TSeckillOrder queryOrderInfoById(String orderId);

  /**
   * 秒杀订单入库
   *
   * @param orderInfoDO
   * @return
   */
  boolean chargeSecKillOrder(TSeckillOrder orderInfoDO);

  /**
   * 订单状态到处理中
   *
   * @param orderInfoDO
   */
  void updateOrderStatusDealing(TSeckillOrder orderInfoDO);

  /**
   * 内部订单查询
   *
   * @return
   */
  Result queryOrder(TSeckillOrder orderInfoDO);
}
