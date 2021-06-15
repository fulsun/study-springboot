package tk.fulsun.gateway.common.service;

import tk.fulsun.gateway.common.dto.Result;
import tk.fulsun.gateway.common.dto.request.ChargeOrderRequest;
import tk.fulsun.gateway.common.dto.request.QueryOrderRequest;

/**
 * @author fsun7
 * @description: 秒杀下单service
 * @date 6/16/2021 10:17 AM
 */
public interface SecKillChargeService {
  /**
   * 秒杀下单前置参数校验
   *
   * @param chargeOrderRequest
   * @param sessionId
   * @return
   */
  boolean checkParamsBeforeSecKillCharge(ChargeOrderRequest chargeOrderRequest, String sessionId);

  /**
   * 秒杀下单前置商品校验
   *
   * @param prodId
   * @param sessionId
   * @return
   */
  boolean checkProdConfigBeforeKillCharge(String prodId, String sessionId);

  /**
   * 秒杀订单入队
   *
   * @param chargeOrderRequest
   * @param sessionId
   * @return
   */
  Result secKillOrderEnqueue(ChargeOrderRequest chargeOrderRequest, String sessionId);

  /**
   * 秒杀查询前置参数校验
   *
   * @param queryOrderRequest
   * @param sessionId
   * @return
   */
  boolean checkParamsBeforeSecKillQuery(QueryOrderRequest queryOrderRequest, String sessionId);

  /**
   * 执行订单查询业务
   *
   * @param queryOrderRequest
   * @param sessionId
   * @return
   */
  Result queryOrder(QueryOrderRequest queryOrderRequest, String sessionId);
}
