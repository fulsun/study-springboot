package tk.fulsun.gateway.common.service.impl;

import com.alibaba.fastjson.JSON;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tk.fulsun.gateway.common.dao.dataobject.TSeckillProduct;
import tk.fulsun.gateway.common.dto.CodeMsg;
import tk.fulsun.gateway.common.dto.Result;
import tk.fulsun.gateway.common.dto.request.ChargeOrderRequest;
import tk.fulsun.gateway.common.dto.request.QueryOrderRequest;
import tk.fulsun.gateway.common.dto.response.ChargeOrderResponse;
import tk.fulsun.gateway.common.dto.response.QueryOrderResponse;
import tk.fulsun.gateway.common.service.SecKillChargeService;
import tk.fulsun.gateway.common.utils.LogExceptionWapper;
import tk.fulsun.gateway.mq.msg.ChargeOrderMsgProtocol;
import tk.fulsun.gateway.mq.msg.MessageProtocolConst;
import tk.fulsun.gateway.mq.producer.SecKillChargeOrderProducer;

/**
 * @author fsun7
 * @description: 秒杀下单service实现
 * @date 6/16/2021 10:41 AM
 */
@Slf4j
@Service(value = "secKillChargeService")
public class SecKillChargeServiceImpl implements SecKillChargeService {
  @Autowired private RedisTemplate redisTemplate;

  @Autowired private SecKillChargeOrderProducer secKillChargeOrderProducer;

  @Autowired private OrderQueryManager orderQueryManager;

  /**
   * 秒杀下单前置参数校验
   *
   * @param chargeOrderRequest
   * @param sessionId
   * @return true为校验通过
   */
  @Override
  public boolean checkParamsBeforeSecKillCharge(
      ChargeOrderRequest chargeOrderRequest, String sessionId) {
    // 入参校验
    if (chargeOrderRequest == null) {
      log.info("sessionId={},下单请求参数chargeOrderRequest为空,返回下单失败", sessionId);
      return false;
    }
    log.info(
        "sessionId={},下单开始,下单请求参数chargeOrderRequest=[{}].",
        sessionId,
        JSON.toJSONString(chargeOrderRequest));
    String userPhoneNum = chargeOrderRequest.getUserPhoneNum();
    String chargePrice = chargeOrderRequest.getChargePrice();
    String prodId = chargeOrderRequest.getProdId();

    if (StringUtils.isBlank(prodId)
        || StringUtils.isBlank(chargePrice)
        || StringUtils.isBlank(userPhoneNum)) {
      log.info("sessionId={},下单必要参数为空,返回下单失败", sessionId);
      return false;
    }
    // 价格合法性校验 是否>0?
    BigDecimal chargePriceDecimal = new BigDecimal(chargePrice);
    if (chargePriceDecimal.longValue() < 0) {
      log.info("sessionId={},商品交易金额小于0,价格非法,返回下单失败", sessionId);
      return false;
    }
    return true;
  }

  /**
   * 秒杀下单前置商品校验
   *
   * @param prodId
   * @param sessionId
   * @return
   */
  @Override
  public boolean checkProdConfigBeforeKillCharge(String prodId, String sessionId) {
    // 商品校验
    TSeckillProduct product = (TSeckillProduct) redisTemplate.opsForValue().get(prodId);
    if (product == null) {
      log.info("sessionId={},prodId={},对应的商品信息不存在,返回下单失败", sessionId, prodId);
      return false;
    }
    return true;
  }

  /**
   * 秒杀订单入队
   *
   * @param chargeOrderRequest
   * @param sessionId
   * @return
   */
  @Override
  public Result secKillOrderEnqueue(ChargeOrderRequest chargeOrderRequest, String sessionId) {

    // 订单号生成,组装秒杀订单消息协议
    String orderId = UUID.randomUUID().toString().replaceAll("-", "");
    String phoneNo = chargeOrderRequest.getUserPhoneNum();

    ChargeOrderMsgProtocol msgProtocol = new ChargeOrderMsgProtocol();
    msgProtocol
        .setUserPhoneNo(phoneNo)
        .setProdId(chargeOrderRequest.getProdId())
        .setChargeMoney(chargeOrderRequest.getChargePrice())
        .setOrderId(orderId);
    String msgBody = msgProtocol.encode();
    log.info("秒杀订单入队,消息协议={}", msgBody);

    DefaultMQProducer mqProducer = secKillChargeOrderProducer.getProducer();
    // 组装RocketMQ消息体
    Message message =
        new Message(MessageProtocolConst.SECKILL_CHARGE_ORDER_TOPIC.getTopic(), msgBody.getBytes());
    try {
      // 消息发送
      SendResult sendResult = mqProducer.send(message);
      // 判断SendStatus
      if (sendResult == null) {
        log.error("sessionId={},秒杀订单消息投递失败,下单失败.msgBody={},sendResult=null", sessionId, msgBody);
        return Result.error(CodeMsg.BIZ_ERROR);
      }
      if (sendResult.getSendStatus() != SendStatus.SEND_OK) {
        log.error("sessionId={},秒杀订单消息投递失败,下单失败.msgBody={},sendResult=null", sessionId, msgBody);
        return Result.error(CodeMsg.BIZ_ERROR);
      }
      ChargeOrderResponse chargeOrderResponse = new ChargeOrderResponse();
      BeanUtils.copyProperties(msgProtocol, chargeOrderResponse);
      log.info(
          "sessionId={},秒杀订单消息投递成功,订单入队.出参chargeOrderResponse={},sendResult={}",
          sessionId,
          chargeOrderResponse.toString(),
          JSON.toJSONString(sendResult));
      return Result.success(CodeMsg.ORDER_INLINE, chargeOrderResponse);
    } catch (Exception e) {
      int sendRetryTimes = mqProducer.getRetryTimesWhenSendFailed();
      log.error(
          "sessionId={},sendRetryTimes={},秒杀订单消息投递异常,下单失败.msgBody={},e={}",
          sessionId,
          sendRetryTimes,
          msgBody,
          LogExceptionWapper.getStackTrace(e));
    }
    return Result.error(CodeMsg.BIZ_ERROR);
  }

  /**
   * 查单前参数校验
   *
   * @param queryOrderRequest
   * @param sessionId
   * @return
   */
  @Override
  public boolean checkParamsBeforeSecKillQuery(
      QueryOrderRequest queryOrderRequest, String sessionId) {
    // 入参校验
    if (queryOrderRequest == null) {
      log.info("sessionId={},查询请求参数queryOrderRequest为空,返回查询失败", sessionId);
      return false;
    }
    log.info(
        "sessionId={},查询开始,查询请求参数queryOrderRequest=[{}].",
        sessionId,
        JSON.toJSONString(queryOrderRequest));

    String userPhoneNum = queryOrderRequest.getUserPhoneNum();
    String prodId = queryOrderRequest.getProdId();

    if (StringUtils.isBlank(prodId) || StringUtils.isBlank(userPhoneNum)) {
      log.info("sessionId={},查询必要参数为空,返回查询失败", sessionId);
      return false;
    }
    return true;
  }

  /**
   * 执行订单查询业务
   *
   * @param queryOrderRequest
   * @param sessionId
   * @return
   */
  @Override
  public Result<QueryOrderResponse> queryOrder(
      QueryOrderRequest queryOrderRequest, String sessionId) {
    return orderQueryManager.queryOrder(queryOrderRequest, sessionId);
  }
}
