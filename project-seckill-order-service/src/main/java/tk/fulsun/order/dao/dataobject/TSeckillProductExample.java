package tk.fulsun.order.dao.dataobject;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TSeckillProductExample {
  protected String orderByClause;

  protected boolean distinct;

  protected List<Criteria> oredCriteria;

  public TSeckillProductExample() {
    oredCriteria = new ArrayList<>();
  }

  public void setOrderByClause(String orderByClause) {
    this.orderByClause = orderByClause;
  }

  public String getOrderByClause() {
    return orderByClause;
  }

  public void setDistinct(boolean distinct) {
    this.distinct = distinct;
  }

  public boolean isDistinct() {
    return distinct;
  }

  public List<Criteria> getOredCriteria() {
    return oredCriteria;
  }

  public void or(Criteria criteria) {
    oredCriteria.add(criteria);
  }

  public Criteria or() {
    Criteria criteria = createCriteriaInternal();
    oredCriteria.add(criteria);
    return criteria;
  }

  public Criteria createCriteria() {
    Criteria criteria = createCriteriaInternal();
    if (oredCriteria.size() == 0) {
      oredCriteria.add(criteria);
    }
    return criteria;
  }

  protected Criteria createCriteriaInternal() {
    Criteria criteria = new Criteria();
    return criteria;
  }

  public void clear() {
    oredCriteria.clear();
    orderByClause = null;
    distinct = false;
  }

  protected abstract static class GeneratedCriteria {
    protected List<Criterion> criteria;

    protected GeneratedCriteria() {
      super();
      criteria = new ArrayList<>();
    }

    public boolean isValid() {
      return criteria.size() > 0;
    }

    public List<Criterion> getAllCriteria() {
      return criteria;
    }

    public List<Criterion> getCriteria() {
      return criteria;
    }

    protected void addCriterion(String condition) {
      if (condition == null) {
        throw new RuntimeException("Value for condition cannot be null");
      }
      criteria.add(new Criterion(condition));
    }

    protected void addCriterion(String condition, Object value, String property) {
      if (value == null) {
        throw new RuntimeException("Value for " + property + " cannot be null");
      }
      criteria.add(new Criterion(condition, value));
    }

    protected void addCriterion(String condition, Object value1, Object value2, String property) {
      if (value1 == null || value2 == null) {
        throw new RuntimeException("Between values for " + property + " cannot be null");
      }
      criteria.add(new Criterion(condition, value1, value2));
    }

    public Criteria andIdIsNull() {
      addCriterion("id is null");
      return (Criteria) this;
    }

    public Criteria andIdIsNotNull() {
      addCriterion("id is not null");
      return (Criteria) this;
    }

    public Criteria andIdEqualTo(Integer value) {
      addCriterion("id =", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotEqualTo(Integer value) {
      addCriterion("id <>", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThan(Integer value) {
      addCriterion("id >", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThanOrEqualTo(Integer value) {
      addCriterion("id >=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThan(Integer value) {
      addCriterion("id <", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThanOrEqualTo(Integer value) {
      addCriterion("id <=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdIn(List<Integer> values) {
      addCriterion("id in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotIn(List<Integer> values) {
      addCriterion("id not in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdBetween(Integer value1, Integer value2) {
      addCriterion("id between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotBetween(Integer value1, Integer value2) {
      addCriterion("id not between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andGmtCreateIsNull() {
      addCriterion("gmt_create is null");
      return (Criteria) this;
    }

    public Criteria andGmtCreateIsNotNull() {
      addCriterion("gmt_create is not null");
      return (Criteria) this;
    }

    public Criteria andGmtCreateEqualTo(LocalDateTime value) {
      addCriterion("gmt_create =", value, "gmtCreate");
      return (Criteria) this;
    }

    public Criteria andGmtCreateNotEqualTo(LocalDateTime value) {
      addCriterion("gmt_create <>", value, "gmtCreate");
      return (Criteria) this;
    }

    public Criteria andGmtCreateGreaterThan(LocalDateTime value) {
      addCriterion("gmt_create >", value, "gmtCreate");
      return (Criteria) this;
    }

    public Criteria andGmtCreateGreaterThanOrEqualTo(LocalDateTime value) {
      addCriterion("gmt_create >=", value, "gmtCreate");
      return (Criteria) this;
    }

    public Criteria andGmtCreateLessThan(LocalDateTime value) {
      addCriterion("gmt_create <", value, "gmtCreate");
      return (Criteria) this;
    }

    public Criteria andGmtCreateLessThanOrEqualTo(LocalDateTime value) {
      addCriterion("gmt_create <=", value, "gmtCreate");
      return (Criteria) this;
    }

    public Criteria andGmtCreateIn(List<LocalDateTime> values) {
      addCriterion("gmt_create in", values, "gmtCreate");
      return (Criteria) this;
    }

    public Criteria andGmtCreateNotIn(List<LocalDateTime> values) {
      addCriterion("gmt_create not in", values, "gmtCreate");
      return (Criteria) this;
    }

    public Criteria andGmtCreateBetween(LocalDateTime value1, LocalDateTime value2) {
      addCriterion("gmt_create between", value1, value2, "gmtCreate");
      return (Criteria) this;
    }

    public Criteria andGmtCreateNotBetween(LocalDateTime value1, LocalDateTime value2) {
      addCriterion("gmt_create not between", value1, value2, "gmtCreate");
      return (Criteria) this;
    }

    public Criteria andGmtUpdateIsNull() {
      addCriterion("gmt_update is null");
      return (Criteria) this;
    }

    public Criteria andGmtUpdateIsNotNull() {
      addCriterion("gmt_update is not null");
      return (Criteria) this;
    }

    public Criteria andGmtUpdateEqualTo(LocalDateTime value) {
      addCriterion("gmt_update =", value, "gmtUpdate");
      return (Criteria) this;
    }

    public Criteria andGmtUpdateNotEqualTo(LocalDateTime value) {
      addCriterion("gmt_update <>", value, "gmtUpdate");
      return (Criteria) this;
    }

    public Criteria andGmtUpdateGreaterThan(LocalDateTime value) {
      addCriterion("gmt_update >", value, "gmtUpdate");
      return (Criteria) this;
    }

    public Criteria andGmtUpdateGreaterThanOrEqualTo(LocalDateTime value) {
      addCriterion("gmt_update >=", value, "gmtUpdate");
      return (Criteria) this;
    }

    public Criteria andGmtUpdateLessThan(LocalDateTime value) {
      addCriterion("gmt_update <", value, "gmtUpdate");
      return (Criteria) this;
    }

    public Criteria andGmtUpdateLessThanOrEqualTo(LocalDateTime value) {
      addCriterion("gmt_update <=", value, "gmtUpdate");
      return (Criteria) this;
    }

    public Criteria andGmtUpdateIn(List<LocalDateTime> values) {
      addCriterion("gmt_update in", values, "gmtUpdate");
      return (Criteria) this;
    }

    public Criteria andGmtUpdateNotIn(List<LocalDateTime> values) {
      addCriterion("gmt_update not in", values, "gmtUpdate");
      return (Criteria) this;
    }

    public Criteria andGmtUpdateBetween(LocalDateTime value1, LocalDateTime value2) {
      addCriterion("gmt_update between", value1, value2, "gmtUpdate");
      return (Criteria) this;
    }

    public Criteria andGmtUpdateNotBetween(LocalDateTime value1, LocalDateTime value2) {
      addCriterion("gmt_update not between", value1, value2, "gmtUpdate");
      return (Criteria) this;
    }

    public Criteria andProdIdIsNull() {
      addCriterion("prod_id is null");
      return (Criteria) this;
    }

    public Criteria andProdIdIsNotNull() {
      addCriterion("prod_id is not null");
      return (Criteria) this;
    }

    public Criteria andProdIdEqualTo(String value) {
      addCriterion("prod_id =", value, "prodId");
      return (Criteria) this;
    }

    public Criteria andProdIdNotEqualTo(String value) {
      addCriterion("prod_id <>", value, "prodId");
      return (Criteria) this;
    }

    public Criteria andProdIdGreaterThan(String value) {
      addCriterion("prod_id >", value, "prodId");
      return (Criteria) this;
    }

    public Criteria andProdIdGreaterThanOrEqualTo(String value) {
      addCriterion("prod_id >=", value, "prodId");
      return (Criteria) this;
    }

    public Criteria andProdIdLessThan(String value) {
      addCriterion("prod_id <", value, "prodId");
      return (Criteria) this;
    }

    public Criteria andProdIdLessThanOrEqualTo(String value) {
      addCriterion("prod_id <=", value, "prodId");
      return (Criteria) this;
    }

    public Criteria andProdIdLike(String value) {
      addCriterion("prod_id like", value, "prodId");
      return (Criteria) this;
    }

    public Criteria andProdIdNotLike(String value) {
      addCriterion("prod_id not like", value, "prodId");
      return (Criteria) this;
    }

    public Criteria andProdIdIn(List<String> values) {
      addCriterion("prod_id in", values, "prodId");
      return (Criteria) this;
    }

    public Criteria andProdIdNotIn(List<String> values) {
      addCriterion("prod_id not in", values, "prodId");
      return (Criteria) this;
    }

    public Criteria andProdIdBetween(String value1, String value2) {
      addCriterion("prod_id between", value1, value2, "prodId");
      return (Criteria) this;
    }

    public Criteria andProdIdNotBetween(String value1, String value2) {
      addCriterion("prod_id not between", value1, value2, "prodId");
      return (Criteria) this;
    }

    public Criteria andProdNameIsNull() {
      addCriterion("prod_name is null");
      return (Criteria) this;
    }

    public Criteria andProdNameIsNotNull() {
      addCriterion("prod_name is not null");
      return (Criteria) this;
    }

    public Criteria andProdNameEqualTo(String value) {
      addCriterion("prod_name =", value, "prodName");
      return (Criteria) this;
    }

    public Criteria andProdNameNotEqualTo(String value) {
      addCriterion("prod_name <>", value, "prodName");
      return (Criteria) this;
    }

    public Criteria andProdNameGreaterThan(String value) {
      addCriterion("prod_name >", value, "prodName");
      return (Criteria) this;
    }

    public Criteria andProdNameGreaterThanOrEqualTo(String value) {
      addCriterion("prod_name >=", value, "prodName");
      return (Criteria) this;
    }

    public Criteria andProdNameLessThan(String value) {
      addCriterion("prod_name <", value, "prodName");
      return (Criteria) this;
    }

    public Criteria andProdNameLessThanOrEqualTo(String value) {
      addCriterion("prod_name <=", value, "prodName");
      return (Criteria) this;
    }

    public Criteria andProdNameLike(String value) {
      addCriterion("prod_name like", value, "prodName");
      return (Criteria) this;
    }

    public Criteria andProdNameNotLike(String value) {
      addCriterion("prod_name not like", value, "prodName");
      return (Criteria) this;
    }

    public Criteria andProdNameIn(List<String> values) {
      addCriterion("prod_name in", values, "prodName");
      return (Criteria) this;
    }

    public Criteria andProdNameNotIn(List<String> values) {
      addCriterion("prod_name not in", values, "prodName");
      return (Criteria) this;
    }

    public Criteria andProdNameBetween(String value1, String value2) {
      addCriterion("prod_name between", value1, value2, "prodName");
      return (Criteria) this;
    }

    public Criteria andProdNameNotBetween(String value1, String value2) {
      addCriterion("prod_name not between", value1, value2, "prodName");
      return (Criteria) this;
    }

    public Criteria andProdStatusIsNull() {
      addCriterion("prod_status is null");
      return (Criteria) this;
    }

    public Criteria andProdStatusIsNotNull() {
      addCriterion("prod_status is not null");
      return (Criteria) this;
    }

    public Criteria andProdStatusEqualTo(Integer value) {
      addCriterion("prod_status =", value, "prodStatus");
      return (Criteria) this;
    }

    public Criteria andProdStatusNotEqualTo(Integer value) {
      addCriterion("prod_status <>", value, "prodStatus");
      return (Criteria) this;
    }

    public Criteria andProdStatusGreaterThan(Integer value) {
      addCriterion("prod_status >", value, "prodStatus");
      return (Criteria) this;
    }

    public Criteria andProdStatusGreaterThanOrEqualTo(Integer value) {
      addCriterion("prod_status >=", value, "prodStatus");
      return (Criteria) this;
    }

    public Criteria andProdStatusLessThan(Integer value) {
      addCriterion("prod_status <", value, "prodStatus");
      return (Criteria) this;
    }

    public Criteria andProdStatusLessThanOrEqualTo(Integer value) {
      addCriterion("prod_status <=", value, "prodStatus");
      return (Criteria) this;
    }

    public Criteria andProdStatusIn(List<Integer> values) {
      addCriterion("prod_status in", values, "prodStatus");
      return (Criteria) this;
    }

    public Criteria andProdStatusNotIn(List<Integer> values) {
      addCriterion("prod_status not in", values, "prodStatus");
      return (Criteria) this;
    }

    public Criteria andProdStatusBetween(Integer value1, Integer value2) {
      addCriterion("prod_status between", value1, value2, "prodStatus");
      return (Criteria) this;
    }

    public Criteria andProdStatusNotBetween(Integer value1, Integer value2) {
      addCriterion("prod_status not between", value1, value2, "prodStatus");
      return (Criteria) this;
    }

    public Criteria andProdStockIsNull() {
      addCriterion("prod_stock is null");
      return (Criteria) this;
    }

    public Criteria andProdStockIsNotNull() {
      addCriterion("prod_stock is not null");
      return (Criteria) this;
    }

    public Criteria andProdStockEqualTo(Integer value) {
      addCriterion("prod_stock =", value, "prodStock");
      return (Criteria) this;
    }

    public Criteria andProdStockNotEqualTo(Integer value) {
      addCriterion("prod_stock <>", value, "prodStock");
      return (Criteria) this;
    }

    public Criteria andProdStockGreaterThan(Integer value) {
      addCriterion("prod_stock >", value, "prodStock");
      return (Criteria) this;
    }

    public Criteria andProdStockGreaterThanOrEqualTo(Integer value) {
      addCriterion("prod_stock >=", value, "prodStock");
      return (Criteria) this;
    }

    public Criteria andProdStockLessThan(Integer value) {
      addCriterion("prod_stock <", value, "prodStock");
      return (Criteria) this;
    }

    public Criteria andProdStockLessThanOrEqualTo(Integer value) {
      addCriterion("prod_stock <=", value, "prodStock");
      return (Criteria) this;
    }

    public Criteria andProdStockIn(List<Integer> values) {
      addCriterion("prod_stock in", values, "prodStock");
      return (Criteria) this;
    }

    public Criteria andProdStockNotIn(List<Integer> values) {
      addCriterion("prod_stock not in", values, "prodStock");
      return (Criteria) this;
    }

    public Criteria andProdStockBetween(Integer value1, Integer value2) {
      addCriterion("prod_stock between", value1, value2, "prodStock");
      return (Criteria) this;
    }

    public Criteria andProdStockNotBetween(Integer value1, Integer value2) {
      addCriterion("prod_stock not between", value1, value2, "prodStock");
      return (Criteria) this;
    }

    public Criteria andProdPriceIsNull() {
      addCriterion("prod_price is null");
      return (Criteria) this;
    }

    public Criteria andProdPriceIsNotNull() {
      addCriterion("prod_price is not null");
      return (Criteria) this;
    }

    public Criteria andProdPriceEqualTo(BigDecimal value) {
      addCriterion("prod_price =", value, "prodPrice");
      return (Criteria) this;
    }

    public Criteria andProdPriceNotEqualTo(BigDecimal value) {
      addCriterion("prod_price <>", value, "prodPrice");
      return (Criteria) this;
    }

    public Criteria andProdPriceGreaterThan(BigDecimal value) {
      addCriterion("prod_price >", value, "prodPrice");
      return (Criteria) this;
    }

    public Criteria andProdPriceGreaterThanOrEqualTo(BigDecimal value) {
      addCriterion("prod_price >=", value, "prodPrice");
      return (Criteria) this;
    }

    public Criteria andProdPriceLessThan(BigDecimal value) {
      addCriterion("prod_price <", value, "prodPrice");
      return (Criteria) this;
    }

    public Criteria andProdPriceLessThanOrEqualTo(BigDecimal value) {
      addCriterion("prod_price <=", value, "prodPrice");
      return (Criteria) this;
    }

    public Criteria andProdPriceIn(List<BigDecimal> values) {
      addCriterion("prod_price in", values, "prodPrice");
      return (Criteria) this;
    }

    public Criteria andProdPriceNotIn(List<BigDecimal> values) {
      addCriterion("prod_price not in", values, "prodPrice");
      return (Criteria) this;
    }

    public Criteria andProdPriceBetween(BigDecimal value1, BigDecimal value2) {
      addCriterion("prod_price between", value1, value2, "prodPrice");
      return (Criteria) this;
    }

    public Criteria andProdPriceNotBetween(BigDecimal value1, BigDecimal value2) {
      addCriterion("prod_price not between", value1, value2, "prodPrice");
      return (Criteria) this;
    }

    public Criteria andVersionIsNull() {
      addCriterion("version is null");
      return (Criteria) this;
    }

    public Criteria andVersionIsNotNull() {
      addCriterion("version is not null");
      return (Criteria) this;
    }

    public Criteria andVersionEqualTo(Integer value) {
      addCriterion("version =", value, "version");
      return (Criteria) this;
    }

    public Criteria andVersionNotEqualTo(Integer value) {
      addCriterion("version <>", value, "version");
      return (Criteria) this;
    }

    public Criteria andVersionGreaterThan(Integer value) {
      addCriterion("version >", value, "version");
      return (Criteria) this;
    }

    public Criteria andVersionGreaterThanOrEqualTo(Integer value) {
      addCriterion("version >=", value, "version");
      return (Criteria) this;
    }

    public Criteria andVersionLessThan(Integer value) {
      addCriterion("version <", value, "version");
      return (Criteria) this;
    }

    public Criteria andVersionLessThanOrEqualTo(Integer value) {
      addCriterion("version <=", value, "version");
      return (Criteria) this;
    }

    public Criteria andVersionIn(List<Integer> values) {
      addCriterion("version in", values, "version");
      return (Criteria) this;
    }

    public Criteria andVersionNotIn(List<Integer> values) {
      addCriterion("version not in", values, "version");
      return (Criteria) this;
    }

    public Criteria andVersionBetween(Integer value1, Integer value2) {
      addCriterion("version between", value1, value2, "version");
      return (Criteria) this;
    }

    public Criteria andVersionNotBetween(Integer value1, Integer value2) {
      addCriterion("version not between", value1, value2, "version");
      return (Criteria) this;
    }
  }

  public static class Criteria extends GeneratedCriteria {
    protected Criteria() {
      super();
    }
  }

  public static class Criterion {
    private String condition;

    private Object value;

    private Object secondValue;

    private boolean noValue;

    private boolean singleValue;

    private boolean betweenValue;

    private boolean listValue;

    private String typeHandler;

    public String getCondition() {
      return condition;
    }

    public Object getValue() {
      return value;
    }

    public Object getSecondValue() {
      return secondValue;
    }

    public boolean isNoValue() {
      return noValue;
    }

    public boolean isSingleValue() {
      return singleValue;
    }

    public boolean isBetweenValue() {
      return betweenValue;
    }

    public boolean isListValue() {
      return listValue;
    }

    public String getTypeHandler() {
      return typeHandler;
    }

    protected Criterion(String condition) {
      super();
      this.condition = condition;
      this.typeHandler = null;
      this.noValue = true;
    }

    protected Criterion(String condition, Object value, String typeHandler) {
      super();
      this.condition = condition;
      this.value = value;
      this.typeHandler = typeHandler;
      if (value instanceof List<?>) {
        this.listValue = true;
      } else {
        this.singleValue = true;
      }
    }

    protected Criterion(String condition, Object value) {
      this(condition, value, null);
    }

    protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
      super();
      this.condition = condition;
      this.value = value;
      this.secondValue = secondValue;
      this.typeHandler = typeHandler;
      this.betweenValue = true;
    }

    protected Criterion(String condition, Object value, Object secondValue) {
      this(condition, value, secondValue, null);
    }
  }
}
