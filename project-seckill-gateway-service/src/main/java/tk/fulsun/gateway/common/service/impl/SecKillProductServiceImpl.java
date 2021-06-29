package tk.fulsun.gateway.common.service.impl;

import com.google.common.base.Preconditions;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tk.fulsun.gateway.common.dao.TSeckillProductMapper;
import tk.fulsun.gateway.common.dao.dataobject.TSeckillProduct;
import tk.fulsun.gateway.common.dao.dataobject.TSeckillProductExample;
import tk.fulsun.gateway.common.dao.dataobject.TSeckillProductExample.Criteria;
import tk.fulsun.gateway.common.service.SecKillProductService;

/**
 * @author fulsun
 * @description: 秒杀商品服务实现
 * @date 6/15/2021 5:18 PM
 */
@Slf4j
@Service(value = "TSeckillProductService")
public class SecKillProductServiceImpl implements SecKillProductService {

  @Autowired private TSeckillProductMapper seckillProductMapper;

  @Autowired private RedisTemplate redisTemplate;

  @Override
  public List<TSeckillProduct> queryTSeckillProductList() {
    return seckillProductMapper.selectByExample(null);
  }

  @Override
  public TSeckillProduct queryProdByProdId(String prodId) {
    TSeckillProductExample example = new TSeckillProductExample();
    Criteria criteria = example.createCriteria();
    criteria.andProdIdEqualTo(prodId);
    List<TSeckillProduct> products = seckillProductMapper.selectByExample(example);
    return products != null && products.size() > 0 ? products.get(0) : null;
  }

  @Override
  public void updateProdInfo(TSeckillProduct updateProdData) {
    seckillProductMapper.updateByPrimaryKeySelective(updateProdData);
  }

  /**
   * 预减库存
   *
   * @param prodId
   * @return
   */
  @Override
  public boolean preReduceProdStock(String prodId) {
    Preconditions.checkNotNull(prodId, "请确保prodId非空!");
    synchronized (this) {
      TSeckillProduct product = (TSeckillProduct) redisTemplate.opsForValue().get(prodId);
      int prodStock = product.getProdStock();
      if (prodStock <= 0) {
        log.info("prodId={},prodStock={},当前秒杀商品库存已不足!", prodId, prodStock);
        return false;
      }

      int afterPreReduce = prodStock - 1;
      if (afterPreReduce < 0) {
        // 预减库存后小于0,说明库存预减失败,库存已不足
        log.info("prodId={} 预减库存失败,当前库存已不足!", prodId);
        return false;
      }
      // 预减库存成功,回写库存
      log.info("prodId={} 预减库存成功,当前扣除后剩余库存={}!", prodId, afterPreReduce);
      product.setProdStock(afterPreReduce);
      redisTemplate.opsForValue().set(prodId, product, 86400, TimeUnit.SECONDS);
      return true;
    }
  }
}
