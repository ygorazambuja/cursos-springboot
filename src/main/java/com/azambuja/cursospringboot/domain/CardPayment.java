package com.azambuja.cursospringboot.domain;

import com.azambuja.cursospringboot.domain.enums.PaymentState;
import javax.persistence.Entity;

@Entity
public class CardPayment extends Payment {
  private static final long serialVersionUID = 1L;

  private Integer numberOfParcels;

  public CardPayment() {}

  public CardPayment(
    Integer id,
    PaymentState paymentState,
    BuyRequest buyRequest,
    Integer numberOfParcels
  ) {
    super(id, paymentState, buyRequest);
    this.numberOfParcels = numberOfParcels;
  }

  public Integer getNumberOfParcels() {
    return numberOfParcels;
  }

  public void setNumberOfParcels(Integer numberOfParcels) {
    this.numberOfParcels = numberOfParcels;
  }
}
