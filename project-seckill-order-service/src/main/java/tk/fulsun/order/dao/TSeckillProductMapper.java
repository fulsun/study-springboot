package tk.fulsun.order.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.fulsun.order.dao.dataobject.TSeckillProduct;
import tk.fulsun.order.dao.dataobject.TSeckillProductExample;

public interface TSeckillProductMapper {
  long countByExample(TSeckillProductExample example);

  int deleteByExample(TSeckillProductExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(TSeckillProduct record);

  int insertSelective(TSeckillProduct record);

  List<TSeckillProduct> selectByExample(TSeckillProductExample example);

  TSeckillProduct selectByPrimaryKey(Integer id);

  int updateByExampleSelective(
      @Param("record") TSeckillProduct record, @Param("example") TSeckillProductExample example);

  int updateByExample(
      @Param("record") TSeckillProduct record, @Param("example") TSeckillProductExample example);

  int updateByPrimaryKeySelective(TSeckillProduct record);

  int updateByPrimaryKey(TSeckillProduct record);
}
