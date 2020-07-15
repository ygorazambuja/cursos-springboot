package com.azambuja.cursospringboot.domain;

import com.azambuja.cursospringboot.domain.enums.PaymentState;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;

@Entity
public class BilletPayment extends Payment implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private Date dueDat;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private Date paymentDate;

  public BilletPayment() {}

  public BilletPayment(
    Integer id,
    PaymentState paymentState,
    BuyRequest buyRequest,
    Date dueDat,
    Date paymentDate
  ) {
    super(id, paymentState, buyRequest);
    this.dueDat = dueDat;
    this.paymentDate = paymentDate;
  }

  public Date getDueDat() {
    return dueDat;
  }

  public void setDueDat(Date dueDat) {
    this.dueDat = dueDat;
  }

  public Date getPaymentDate() {
    return paymentDate;
  }

  public void setPaymentDate(Date paymentDate) {
    this.paymentDate = paymentDate;
  }
}
