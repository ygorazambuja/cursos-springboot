package com.azambuja.cursospringboot.domain.enums;

public enum ClientType {
  FISICPERSON(1, "Fisic Person"),
  JURIDICPERSON(2, "Juridic Person");

  private int code;
  private String description;

  ClientType() {}

  ClientType(int code, String description) {
    this.code = code;
    this.description = description;
  }

  public int getCode() {
    return code;
  }

  public String getDescription() {
    return description;
  }

  public static ClientType toEnum(Integer code) {
    if (code == null) {
      return null;
    }
    for (ClientType clientType : ClientType.values()) {
      if (code.equals(clientType.getCode())) {
        return clientType;
      }
    }
    throw new IllegalArgumentException("Invalid Requisition, Code Error: " + code);
  }
}
