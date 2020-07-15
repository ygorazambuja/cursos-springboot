package com.azambuja.cursospringboot.domain;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ItemRequestPK implements Serializable {
  private static final long serialVersionUID = 1L;

  @ManyToOne
  @JoinColumn(name = "buy_request_id")
  private BuyRequest buyRequest;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  public BuyRequest getBuyRequest() {
    return buyRequest;
  }

  public void setBuyRequest(BuyRequest buyRequest) {
    this.buyRequest = buyRequest;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ItemRequestPK that = (ItemRequestPK) o;

    if (
      buyRequest != null ? !buyRequest.equals(that.buyRequest) : that.buyRequest != null
    ) return false;
    return product != null ? product.equals(that.product) : that.product == null;
  }

  @Override
  public int hashCode() {
    int result = buyRequest != null ? buyRequest.hashCode() : 0;
    result = 31 * result + (product != null ? product.hashCode() : 0);
    return result;
  }
}
