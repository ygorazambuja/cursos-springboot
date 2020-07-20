package com.azambuja.cursospringboot.domain;

import com.azambuja.cursospringboot.domain.enums.PaymentState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
public abstract class Payment implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private Integer id;

  private Integer paymentState;

  @JsonIgnore
  @OneToOne
  @JoinColumn(name = "buy_request_id")
  @MapsId
  private BuyRequest buyRequest;

  public Payment() {}

  public Payment(Integer id, PaymentState paymentState, BuyRequest buyRequest) {
    super();
    this.id = id;
    this.paymentState = (paymentState == null) ? null : paymentState.getCode();
    this.buyRequest = buyRequest;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public PaymentState getPaymentState() {
    return PaymentState.toEnum(paymentState);
  }

  public void setPaymentState(PaymentState paymentState) {
    this.paymentState = paymentState.getCode();
  }

  public BuyRequest getBuyRequest() {
    return buyRequest;
  }

  public void setBuyRequest(BuyRequest buyRequest) {
    this.buyRequest = buyRequest;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Payment payment = (Payment) o;

    return id != null ? id.equals(payment.id) : payment.id == null;
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }
}
