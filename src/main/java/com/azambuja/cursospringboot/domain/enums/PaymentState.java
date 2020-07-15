package com.azambuja.cursospringboot.domain.enums;

public enum PaymentState {
  PENDING(1, "Pending"),
  SETTLED(2, "Settled"),
  CANCELED(3, "Canceled");

  private int code;
  private String description;

  PaymentState(int code, String description) {
    this.code = code;
    this.description = description;
  }

  public int getCode() {
    return code;
  }

  public String getDescription() {
    return description;
  }

  public static PaymentState toEnum(Integer code) {
    if (code == null) {
      return null;
    }
    for (PaymentState paymentState : PaymentState.values()) {
      if (code.equals(paymentState.getCode())) {
        return paymentState;
      }
    }
    throw new IllegalArgumentException("Invalid Requisition, Code Error: " + code);
  }
}
