package tk.fulsun.order.mq.listener;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.fulsun.order.dao.dataobject.TSeckillOrder;
import tk.fulsun.order.dao.dataobject.TSeckillProduct;
import tk.fulsun.order.dto.CodeMsg;
import tk.fulsun.order.dto.Result;
import tk.fulsun.order.mq.msg.ChargeOrderMsgProtocol;
import tk.fulsun.order.service.SecKillOrderService;
import tk.fulsun.order.service.SecKillProductService;
import tk.fulsun.order.utils.LogExceptionWapper;

/**
 * @author fulsun
 * @description: 秒杀订单消费监听回调
 * @date 6/16/2021 3:29 PM
 */
@Slf4j
@Component
public class SecKillChargeOrderListenerImpl implements MessageListenerConcurrently {

  @Autowired private SecKillOrderService secKillOrderService;
  @Autowired private SecKillProductService secKillProductService;

  /**
   * 秒杀核心消费逻辑
   *
   * @param msgs
   * @param context
   * @return
   */
  @Override
  public ConsumeConcurrentlyStatus consumeMessage(
      List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
    try {
      for (MessageExt msg : msgs) {
        // 消息解码
        String message = new String(msg.getBody());
        int reconsumeTimes = msg.getReconsumeTimes();
        String msgId = msg.getMsgId();
        String logSuffix = "msgId=" + msgId + ",reconsumeTimes=" + reconsumeTimes;
        log.info("[秒杀订单消费者]-SecKillChargeOrderConsumer-接收到消息,message={},{}", message, logSuffix);

        // 反序列化协议实体
        ChargeOrderMsgProtocol chargeOrderMsgProtocol = new ChargeOrderMsgProtocol();
        chargeOrderMsgProtocol.decode(message);
        log.info(
            "[秒杀订单消费者]-SecKillChargeOrderConsumer-反序列化为秒杀入库订单实体chargeOrderMsgProtocol={},{}",
            chargeOrderMsgProtocol.toString(),
            logSuffix);

        // 消费幂等:查询orderId对应订单是否已存在
        String orderId = chargeOrderMsgProtocol.getOrderId();
        TSeckillOrder orderInfoDobj = secKillOrderService.queryOrderInfoById(orderId);
        if (orderInfoDobj != null) {
          log.info(
              "[秒杀订单消费者]-SecKillChargeOrderConsumer-当前订单已入库,不需要重复消费!,orderId={},{}",
              orderId,
              logSuffix);
          return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }

        // 业务幂等:同一个prodId+同一个userPhoneNo只有一个秒杀订单，通过订单id唯一保证 delete/insert 幂等
        TSeckillOrder orderInfo = new TSeckillOrder();
        orderInfo.setProdId(chargeOrderMsgProtocol.getProdId());
        orderInfo.setUserPhoneno(chargeOrderMsgProtocol.getUserPhoneNo());
        Result result = secKillOrderService.queryOrder(orderInfo);
        if (result != null && result.getCode().equals(CodeMsg.SUCCESS.getCode())) {
          log.info(
              "[秒杀订单消费者]-SecKillChargeOrderConsumer-当前用户={},秒杀的产品={}订单已存在,不得重复秒杀,orderId={}",
              orderInfo.getUserPhoneno(),
              orderInfo.getProdId(),
              orderId);
          return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }

        // 秒杀订单入库
        TSeckillOrder orderInfoDB = new TSeckillOrder();
        BeanUtils.copyProperties(chargeOrderMsgProtocol, orderInfoDB);
        orderInfoDB.setUserPhoneno(chargeOrderMsgProtocol.getUserPhoneNo());

        // 库存校验
        String prodId = chargeOrderMsgProtocol.getProdId();
        TSeckillProduct productDobj = secKillProductService.querySecKillProductByProdId(prodId);
        // 取库存校验
        int currentProdStock = productDobj.getProdStock();
        if (currentProdStock <= 0) {
          log.info(
              "[decreaseProdStock]当前商品已售罄,消息消费成功!prodId={},currStock={}", prodId, currentProdStock);
          return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        // 正式下单
        if (secKillOrderService.chargeSecKillOrder(orderInfoDB)) {
          log.info(
              "[秒杀订单消费者]-SecKillChargeOrderConsumer-秒杀订单入库成功,消息消费成功!,入库实体orderInfoDO={},{}",
              orderInfoDB.toString(),
              logSuffix);
          // 模拟订单处理，直接修改订单状态为处理中
          secKillOrderService.updateOrderStatusDealing(orderInfoDB);
          return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
      }
    } catch (Exception e) {
      log.info("[秒杀订单消费者]消费异常,e={}", LogExceptionWapper.getStackTrace(e));
    }
    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
  }
}
