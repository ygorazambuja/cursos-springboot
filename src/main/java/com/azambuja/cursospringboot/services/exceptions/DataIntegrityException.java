package com.azambuja.cursospringboot.services.exceptions;

public class DataIntegrityException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public DataIntegrityException(String msg) {
    super(msg);
  }

  public DataIntegrityException(String message, Throwable cause) {
    super(message, cause);
  }
}
