package tk.fulsun.gateway.common.init;

import com.alibaba.fastjson.JSON;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import tk.fulsun.gateway.common.dao.dataobject.TSeckillProduct;
import tk.fulsun.gateway.common.service.SecKillProductService;

/**
 * @author fsun7
 * @description: 秒杀商品初始化加载，正式生产环境是有一个商品服务的，这里简化处理了
 * @date 6/15/2021 5:15 PM
 */
@Component
@Slf4j
public class SecKillProductConfig implements InitializingBean {

  @Autowired private RedisTemplate redisTemplate;

  @Autowired private SecKillProductService secKillProductService;

  @Override
  public void afterPropertiesSet() throws Exception {
    List<TSeckillProduct> killProductList = secKillProductService.queryTSeckillProductList();
    if (killProductList == null) {
      throw new RuntimeException("请确保数据库中存在秒杀商品!");
    }
    //
    killProductList.stream()
        .forEach(
            (pojo -> {
              String prodId = pojo.getProdId();
              redisTemplate.opsForValue().set(prodId, pojo, 86400, TimeUnit.SECONDS);
            }));
    log.info("[SecKillProductConfig]初始化秒杀配置完成,商品信息=[{}]", JSON.toJSONString(killProductList));
  }
}
