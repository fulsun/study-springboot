package tk.fulsun.demo.exception.pojo.response;

import java.util.List;
import lombok.Data;

/**
 * @author fulsun
 * @date 7/1/2021
 */
@Data
public class QueryData<T> {
  /** 数据列表 */
  private List<T> records;
  /** 总记录数 */
  private long totalCount;
  /** 当前页，从1开始 */
  private long pageNo;
  /** 分页大小 */
  private long pageSize;

  public QueryData() {}

  public QueryData(List<T> records, long totalCount, int pageNo, int pageSize) {
    this.records = records;
    this.totalCount = totalCount;
    this.pageNo = pageNo;
    this.pageSize = pageSize;
  }
}
