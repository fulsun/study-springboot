package tk.fulsun.order.service.impl;

import com.alibaba.fastjson.JSON;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.fulsun.order.dao.TSeckillOrderMapper;
import tk.fulsun.order.dao.dataobject.TSeckillOrder;
import tk.fulsun.order.dao.dataobject.TSeckillOrderExample;
import tk.fulsun.order.dao.dataobject.TSeckillOrderExample.Criteria;
import tk.fulsun.order.dao.dataobject.TSeckillProduct;
import tk.fulsun.order.dto.CodeMsg;
import tk.fulsun.order.dto.Result;
import tk.fulsun.order.dto.response.QueryOrderResponse;
import tk.fulsun.order.service.SecKillOrderService;
import tk.fulsun.order.service.SecKillProductService;
import tk.fulsun.order.utils.LogExceptionWapper;

/**
 * @author fsun7
 * @description: 秒杀订单本地service实现
 * @date 6/16/2021 4:20 PM
 */
@Slf4j
@Service(value = "secKillOrderService")
public class SecKillOrderServiceImpl implements SecKillOrderService {

  @Autowired private TSeckillOrderMapper secKillOrderMapper;

  @Autowired SecKillProductService secKillProductService;

  /**
   * 根据订单号查询订单明细
   *
   * @param orderId
   * @return
   */
  @Override
  public TSeckillOrder queryOrderInfoById(String orderId) {
    TSeckillOrder orderInfo = new TSeckillOrder();
    orderInfo.setOrderId(orderId);
    TSeckillOrderExample record = new TSeckillOrderExample();
    Criteria criteria = record.createCriteria();
    criteria.andOrderIdEqualTo(orderId).andRecordStatusEqualTo((byte) 0);
    List<TSeckillOrder> seckillOrders = secKillOrderMapper.selectByExample(record);
    return seckillOrders != null && seckillOrders.size() > 0 ? seckillOrders.get(0) : null;
  }

  /**
   * 秒杀订单入库
   *
   * @param orderInfo
   * @return
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public boolean chargeSecKillOrder(TSeckillOrder orderInfo) {
    int insertCount = 0;
    String orderId = orderInfo.getOrderId();
    String prodId = orderInfo.getProdId();

    // 减库存
    if (!secKillProductService.decreaseProdStock(prodId)) {
      log.info("[insertSecKillOrder]orderId={},prodId={},下单前减库存失败,下单失败!", orderId, prodId);
      // TODO 此处可给用户发送通知，告知秒杀下单失败，原因：商品已售罄
      return false;
    }
    // 设置产品名称
    TSeckillProduct productInfo = secKillProductService.querySecKillProductByProdId(prodId);
    orderInfo.setProdName(productInfo.getProdName());
    try {
      // 记录状态 0 正常
      orderInfo.setRecordStatus((byte) 0);
      //订单状态，1 初始化
      orderInfo.setOrderStatus((byte) 1);
      insertCount = secKillOrderMapper.insertSelective(orderInfo);
    } catch (Exception e) {
      log.error(
          "[insertSecKillOrder]orderId={},秒杀订单入库[异常],事务回滚,e={}",
          orderId,
          LogExceptionWapper.getStackTrace(e));
      String message = String.format("[insertSecKillOrder]orderId=%s,秒杀订单入库[异常],事务回滚", orderId);
      throw new RuntimeException(message);
    }
    if (insertCount != 1) {
      log.error("[insertSecKillOrder]orderId={},秒杀订单入库[失败],事务回滚,e={}", orderId);
      String message = String.format("[insertSecKillOrder]orderId=%s,秒杀订单入库[失败],事务回滚", orderId);
      throw new RuntimeException(message);
    }
    return true;
  }

  /**
   * 修改订单状态到处理中
   *
   * @param orderInfo
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public void updateOrderStatusDealing(TSeckillOrder orderInfo) {
    int updateCount = 0;
    String orderId = orderInfo.getOrderId();
    try {
      // 订单单状态修改为处理中
      orderInfo.setOrderStatus((byte) 2);
      TSeckillOrderExample example = new TSeckillOrderExample();
      example.createCriteria().andOrderStatusEqualTo((byte) 1).andOrderIdEqualTo(orderId);
      updateCount = secKillOrderMapper.updateByExampleSelective(orderInfo, example);
    } catch (Exception e) {
      log.error(
          "[updateOrderStatusDealing]orderId={},订单状态修改为处理中[异常],事务回滚,e={}",
          orderId,
          LogExceptionWapper.getStackTrace(e));
      String message =
          String.format("[updateOrderStatusDealing]orderId=%s,订单状态修改为处理中[异常],事务回滚", orderId);
      throw new RuntimeException(message);
    }
    if (updateCount != 1) {
      log.error("[updateOrderStatusDealing]orderId={},订单状态修改为处理中[失败],事务回滚,e={}", orderId);
      String message =
          String.format("[updateOrderStatusDealing]orderId=%s,订单状态修改为处理中[失败],事务回滚", orderId);
      throw new RuntimeException(message);
    }
    log.info("[updateOrderStatusDealing]orderId={},订单状态修改为处理中[成功]", orderId);
  }

  /**
   * 内部订单查询
   *
   * @return
   */
  @Override
  public Result queryOrder(TSeckillOrder orderInfo) {

    log.info("[queryOrder]-内部订单查询开始,入参={}", orderInfo);
    // 查询
    try {
      TSeckillOrderExample example = new TSeckillOrderExample();
      Criteria criteria = example.createCriteria().andRecordStatusEqualTo((byte) 0);
      criteria.andProdIdEqualTo(orderInfo.getProdId());
      criteria.andUserPhonenoEqualTo(orderInfo.getUserPhoneno());
      List<TSeckillOrder> tSeckillOrders = secKillOrderMapper.selectByExample(example);
      log.info("[queryOrder]-orderInfobjs={}", tSeckillOrders);
      if (tSeckillOrders == null || tSeckillOrders.size() <= 0) {
        return Result.error(CodeMsg.BIZ_ERROR);
      }
      orderInfo = tSeckillOrders.get(0);
    } catch (Exception e) {
      log.error("[queryOrder]-内部订单查询异常,e={}", LogExceptionWapper.getStackTrace(e));
      return Result.error(CodeMsg.SERVER_ERROR);
    }
    QueryOrderResponse queryOrderResponse = new QueryOrderResponse();
    BeanUtils.copyProperties(orderInfo, queryOrderResponse);
    Result result = Result.success(CodeMsg.SUCCESS, queryOrderResponse);
    log.info("[queryOrder]-内部订单查询,出参={}", JSON.toJSONString(result));
    return result;
  }
}
