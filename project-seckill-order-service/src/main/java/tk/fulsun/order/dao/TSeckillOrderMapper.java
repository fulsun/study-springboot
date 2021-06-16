package tk.fulsun.order.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.fulsun.order.dao.dataobject.TSeckillOrder;
import tk.fulsun.order.dao.dataobject.TSeckillOrderExample;

public interface TSeckillOrderMapper {
  long countByExample(TSeckillOrderExample example);

  int deleteByExample(TSeckillOrderExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(TSeckillOrder record);

  int insertSelective(TSeckillOrder record);

  List<TSeckillOrder> selectByExample(TSeckillOrderExample example);

  TSeckillOrder selectByPrimaryKey(Integer id);

  int updateByExampleSelective(
      @Param("record") TSeckillOrder record, @Param("example") TSeckillOrderExample example);

  int updateByExample(
      @Param("record") TSeckillOrder record, @Param("example") TSeckillOrderExample example);

  int updateByPrimaryKeySelective(TSeckillOrder record);

  int updateByPrimaryKey(TSeckillOrder record);
}
