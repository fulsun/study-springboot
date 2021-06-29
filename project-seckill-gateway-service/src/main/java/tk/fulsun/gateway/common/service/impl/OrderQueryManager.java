package tk.fulsun.gateway.common.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import tk.fulsun.gateway.common.dao.dataobject.TSeckillOrder;
import tk.fulsun.gateway.common.dto.CodeMsg;
import tk.fulsun.gateway.common.dto.Result;
import tk.fulsun.gateway.common.dto.request.QueryOrderRequest;
import tk.fulsun.gateway.common.dto.response.QueryOrderResponse;

/**
 * @author fulsun
 * @description: 订单查询返回层
 * @date 6/16/2021 1:22 PM
 */
@Slf4j
@Service
public class OrderQueryManager {
  @Autowired RestTemplate restTemplate;

  @Value("${seckill.order.query.url}")
  private String queryUrl;

  /**
   * 远程订单查询代理
   *
   * @param queryOrderRequest
   * @param sessionId
   * @return
   */
  public Result<QueryOrderResponse> queryOrder(
      QueryOrderRequest queryOrderRequest, String sessionId) {

    // 请求参数
    String userPhoneNum = queryOrderRequest.getUserPhoneNum();
    String prodId = queryOrderRequest.getProdId();
    // 1. 设置请求头
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    // 2. 设置请求参数
    MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<String, String>();
    requestParam.add("userPhoneNum", userPhoneNum);
    requestParam.add("prodId", prodId);
    log.info("请求远端查单地址:{}, 下单入参:{}", queryUrl, requestParam.toString());
    // 3. 请求开始
    HttpEntity<MultiValueMap<String, String>> requestEntity =
        new HttpEntity<MultiValueMap<String, String>>(requestParam, headers);
    ResponseEntity<String> queryResponse =
        restTemplate.exchange(queryUrl, HttpMethod.POST, requestEntity, String.class);
    // 4. 返回参校验
    if (queryResponse == null) {
      log.info("[queryOrder]-当前订单查询返回为空,sessionId={}", sessionId);
      return Result.error(CodeMsg.SECKILL_ORDER_NOT_EXIST);
    }
    // 5. 解析返回参
    String queryOrderBody = queryResponse.getBody();
    log.error("[queryOrder]-调用内部订单查询接口出参:[{}],sessionId={}", queryOrderBody, sessionId);
    if (StringUtils.isBlank(queryOrderBody)) {
      log.info("[queryOrder]-当前订单查询返回为空,sessionId={}", sessionId);
      return Result.error(CodeMsg.SECKILL_ORDER_NOT_EXIST);
    }
    // 6. 参数反序列化
    Result resultData = JSON.parseObject(queryOrderBody, Result.class);

    if (resultData.getCode().equals(CodeMsg.BIZ_ERROR.getCode())) {
      log.error(
          "[queryOrder]-当前订单查询失败.出参:[{}],sessionId={}", JSON.toJSONString(resultData), sessionId);
      return Result.error(CodeMsg.SECKILL_ORDER_NOT_EXIST);
    }
    if (!resultData.getCode().equals(CodeMsg.SUCCESS.getCode())) {
      log.error(
          "[queryOrder]-当前订单查询失败.出参:[{}],sessionId={}", JSON.toJSONString(resultData), sessionId);
      return Result.error(CodeMsg.BIZ_ERROR);
    }
    // 查询成功
    if (resultData.getCode().equals(CodeMsg.SUCCESS.getCode())) {
      QueryOrderResponse queryOrderResponse = new QueryOrderResponse();
      TSeckillOrder orderDetail = (TSeckillOrder) resultData.getData();
      BeanUtils.copyProperties(orderDetail, queryOrderResponse);
      Result<QueryOrderResponse> successResult =
          new Result<>(CodeMsg.SUCCESS.getCode(), CodeMsg.SUCCESS.getMsg(), queryOrderResponse);
      log.info(
          "[queryOrder]-当前订单查询成功.出参:[{}],sessionId={}",
          JSON.toJSONString(successResult),
          sessionId);
      return successResult;
    }
    return Result.error(CodeMsg.SECKILL_ORDER_NOT_EXIST);
  }
}
