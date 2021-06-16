package tk.fulsun.order.service.impl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.fulsun.order.dao.TSeckillProductMapper;
import tk.fulsun.order.dao.dataobject.TSeckillProduct;
import tk.fulsun.order.dao.dataobject.TSeckillProductExample;
import tk.fulsun.order.service.SecKillProductService;
import tk.fulsun.order.utils.LogExceptionWapper;

/**
 * @author fsun7
 * @description: 秒杀商品服务实现
 * @date 6/16/2021 4:20 PM
 */
@Slf4j
@Service(value = "secKillProductService")
public class SecKillProductServiceImpl implements SecKillProductService {

    @Autowired
    TSeckillProductMapper secKillProductMapper;

    /**
     * 获取秒杀商品列表
     *
     * @return
     */
    @Override
    public List<TSeckillProduct> querySecKillProductList() {
      TSeckillProductExample example = new TSeckillProductExample();
      example.createCriteria().andProdStatusEqualTo(0);
      return secKillProductMapper.selectByExample(example);
    }

    /**
     * 根据商品id查询产品明细
     *
     * @param prodId
     * @return
     */
    @Override
    public TSeckillProduct querySecKillProductByProdId(String prodId) {
        TSeckillProductExample example = new TSeckillProductExample();
        example.createCriteria().andProdIdEqualTo(prodId);
        List<TSeckillProduct> seckillProducts = secKillProductMapper.selectByExample(example);
        return seckillProducts != null && seckillProducts.size() > 0 ? seckillProducts.get(0) : null;
    }

    /**
     * 减库存,基于乐观锁
     *
     * @param prodId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean decreaseProdStock(String prodId) {

        TSeckillProduct productDobj = querySecKillProductByProdId(prodId);
        // 取库存
        int currentProdStock = productDobj.getProdStock();
        // 取版本号
        int beforeVersion = productDobj.getVersion();
        TSeckillProduct productDO = new TSeckillProduct();
        productDO.setProdStock(currentProdStock - 1);
        productDO.setVersion(beforeVersion + 1);
        int updateCount = 0;
        try {
            // 更新操作
            TSeckillProductExample example = new TSeckillProductExample();
            example.createCriteria()
                    .andProdStockGreaterThanOrEqualTo(1)
                    .andProdIdEqualTo(prodId)
                    .andVersionEqualTo(beforeVersion);
            updateCount = secKillProductMapper.updateByExampleSelective(productDO, example);
        } catch (Exception e) {
            log.error(
                    "[decreaseProdStock]prodId={},商品减库存[异常],事务回滚,e={}",
                    prodId,
                    LogExceptionWapper.getStackTrace(e));
            String message = String.format("[decreaseProdStock]prodId=%s,商品减库存[异常],事务回滚", prodId);
            throw new RuntimeException(message);
        }
        if (updateCount != 1) {
            log.error("[decreaseProdStock]prodId={},商品减库存[失败],事务回滚,e={}", prodId);
            String message = String.format("[decreaseProdStock]prodId=%s,商品减库存[失败],事务回滚", prodId);
            throw new RuntimeException(message);
        }
        log.info("[decreaseProdStock]当前减库存[成功],prodId={},剩余库存={}", prodId, currentProdStock - 1);
        return true;
    }
}
