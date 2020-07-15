package com.azambuja.cursospringboot.resources.exceptions;

import com.azambuja.cursospringboot.services.exceptions.DataIntegrityException;
import com.azambuja.cursospringboot.services.exceptions.ObjectNotFoundException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler(ObjectNotFoundException.class)
  public ResponseEntity<StandardError> objectNotFound(
    ObjectNotFoundException objectNotFoundException,
    HttpServletRequest httpServletRequest
  ) {
    StandardError error = new StandardError(
      HttpStatus.NOT_FOUND.value(),
      objectNotFoundException.getMessage(),
      System.currentTimeMillis()
    );
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  @ExceptionHandler(DataIntegrityException.class)
  public ResponseEntity<StandardError> objectNotFound(
    DataIntegrityException dataIntegrityException,
    HttpServletRequest httpServletRequest
  ) {
    StandardError error = new StandardError(
      HttpStatus.BAD_REQUEST.value(),
      dataIntegrityException.getMessage(),
      System.currentTimeMillis()
    );
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }
}
