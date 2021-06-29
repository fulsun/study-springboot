package tk.fulsun.gateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tk.fulsun.gateway.common.dto.CodeMsg;
import tk.fulsun.gateway.common.dto.Result;
import tk.fulsun.gateway.common.dto.request.ChargeOrderRequest;
import tk.fulsun.gateway.common.dto.request.QueryOrderRequest;
import tk.fulsun.gateway.common.service.SecKillChargeService;
import tk.fulsun.gateway.common.service.SecKillProductService;

/**
 * @author fulsun
 * @description: TODO
 * @date 6/16/2021 1:41 PM
 */
@Slf4j
@RestController
@RequestMapping("api")
public class OrderChargeController {
  @Autowired private SecKillChargeService secKillChargeService;
  @Autowired private SecKillProductService secKillProductService;

  /**
   * 平台下单接口
   *
   * @param chargeOrderRequest
   * @return
   */
  @PostMapping(value = "charge")
  public Result chargeOrder(@RequestBody ChargeOrderRequest chargeOrderRequest) {
    ServletRequestAttributes attributes =
        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    String sessionId = attributes.getSessionId();
    // 下单前置参数校验
    if (!secKillChargeService.checkParamsBeforeSecKillCharge(chargeOrderRequest, sessionId)) {
      return Result.error(CodeMsg.PARAM_INVALID);
    }
    // 前置商品校验
    String prodId = chargeOrderRequest.getProdId();
    if (!secKillChargeService.checkProdConfigBeforeKillCharge(prodId, sessionId)) {
      return Result.error(CodeMsg.PRODUCT_NOT_EXIST);
    }
    // 前置预减库存
    if (!secKillProductService.preReduceProdStock(prodId)) {
      return Result.error(CodeMsg.PRODUCT_STOCK_NOT_ENOUGH);
    }
    // 秒杀订单入队
    return secKillChargeService.secKillOrderEnqueue(chargeOrderRequest, sessionId);
  }

  /**
   * 平台查单接口
   *
   * @param queryOrderRequest
   * @return
   */
  @GetMapping(value = "query")
  public Result queryOrder(@ModelAttribute QueryOrderRequest queryOrderRequest) {
    ServletRequestAttributes attributes =
        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    String sessionId = attributes.getSessionId();
    // 查询前置参数校验
    if (!secKillChargeService.checkParamsBeforeSecKillQuery(queryOrderRequest, sessionId)) {
      return Result.error(CodeMsg.PARAM_INVALID);
    }
    // 查询订单
    return secKillChargeService.queryOrder(queryOrderRequest, sessionId);
  }
}
