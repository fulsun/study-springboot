package tk.fulsun.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.fulsun.order.dao.dataobject.TSeckillOrder;
import tk.fulsun.order.dto.Result;
import tk.fulsun.order.dto.request.QueryOrderRequest;
import tk.fulsun.order.service.SecKillOrderService;

/**
 * @Description 订单充值接口
 * @Date 2021/6/16
 * @Created by 凉月-文
 */
@Slf4j
@RestController
@RequestMapping("api")
public class OrderController {
    @Autowired
    private SecKillOrderService secKillOrderService;

    /**
     * 平台内部查单接口
     * @param queryOrderRequest
     * @return
     */
    @RequestMapping(value = "query", method = {RequestMethod.POST})
    public Result queryOrder(@RequestBody QueryOrderRequest queryOrderRequest) {
        TSeckillOrder orderInfo = new TSeckillOrder();
        orderInfo.setProdId(queryOrderRequest.getProdId());
        orderInfo.setUserPhoneno(queryOrderRequest.getUserPhoneNum());
        // 查询订单
        return secKillOrderService.queryOrder(orderInfo);
    }
}
