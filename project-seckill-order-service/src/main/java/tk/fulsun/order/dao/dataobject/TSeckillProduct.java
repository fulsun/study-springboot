package tk.fulsun.order.dao.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TSeckillProduct implements Serializable {
  private Integer id;

  private LocalDateTime gmtCreate;

  private LocalDateTime gmtUpdate;

  private String prodId;

  private String prodName;

  private Integer prodStatus;

  private Integer prodStock;

  private BigDecimal prodPrice;

  private Integer version;

  private static final long serialVersionUID = 1L;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public LocalDateTime getGmtCreate() {
    return gmtCreate;
  }

  public void setGmtCreate(LocalDateTime gmtCreate) {
    this.gmtCreate = gmtCreate;
  }

  public LocalDateTime getGmtUpdate() {
    return gmtUpdate;
  }

  public void setGmtUpdate(LocalDateTime gmtUpdate) {
    this.gmtUpdate = gmtUpdate;
  }

  public String getProdId() {
    return prodId;
  }

  public void setProdId(String prodId) {
    this.prodId = prodId == null ? null : prodId.trim();
  }

  public String getProdName() {
    return prodName;
  }

  public void setProdName(String prodName) {
    this.prodName = prodName == null ? null : prodName.trim();
  }

  public Integer getProdStatus() {
    return prodStatus;
  }

  public void setProdStatus(Integer prodStatus) {
    this.prodStatus = prodStatus;
  }

  public Integer getProdStock() {
    return prodStock;
  }

  public void setProdStock(Integer prodStock) {
    this.prodStock = prodStock;
  }

  public BigDecimal getProdPrice() {
    return prodPrice;
  }

  public void setProdPrice(BigDecimal prodPrice) {
    this.prodPrice = prodPrice;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }
    if (that == null) {
      return false;
    }
    if (getClass() != that.getClass()) {
      return false;
    }
    TSeckillProduct other = (TSeckillProduct) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getGmtCreate() == null
            ? other.getGmtCreate() == null
            : this.getGmtCreate().equals(other.getGmtCreate()))
        && (this.getGmtUpdate() == null
            ? other.getGmtUpdate() == null
            : this.getGmtUpdate().equals(other.getGmtUpdate()))
        && (this.getProdId() == null
            ? other.getProdId() == null
            : this.getProdId().equals(other.getProdId()))
        && (this.getProdName() == null
            ? other.getProdName() == null
            : this.getProdName().equals(other.getProdName()))
        && (this.getProdStatus() == null
            ? other.getProdStatus() == null
            : this.getProdStatus().equals(other.getProdStatus()))
        && (this.getProdStock() == null
            ? other.getProdStock() == null
            : this.getProdStock().equals(other.getProdStock()))
        && (this.getProdPrice() == null
            ? other.getProdPrice() == null
            : this.getProdPrice().equals(other.getProdPrice()))
        && (this.getVersion() == null
            ? other.getVersion() == null
            : this.getVersion().equals(other.getVersion()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
    result = prime * result + ((getGmtUpdate() == null) ? 0 : getGmtUpdate().hashCode());
    result = prime * result + ((getProdId() == null) ? 0 : getProdId().hashCode());
    result = prime * result + ((getProdName() == null) ? 0 : getProdName().hashCode());
    result = prime * result + ((getProdStatus() == null) ? 0 : getProdStatus().hashCode());
    result = prime * result + ((getProdStock() == null) ? 0 : getProdStock().hashCode());
    result = prime * result + ((getProdPrice() == null) ? 0 : getProdPrice().hashCode());
    result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", gmtCreate=").append(gmtCreate);
    sb.append(", gmtUpdate=").append(gmtUpdate);
    sb.append(", prodId=").append(prodId);
    sb.append(", prodName=").append(prodName);
    sb.append(", prodStatus=").append(prodStatus);
    sb.append(", prodStock=").append(prodStock);
    sb.append(", prodPrice=").append(prodPrice);
    sb.append(", version=").append(version);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
