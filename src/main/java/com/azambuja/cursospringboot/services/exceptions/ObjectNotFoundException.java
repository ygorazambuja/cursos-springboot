package com.azambuja.cursospringboot.services.exceptions;

public class ObjectNotFoundException extends  RuntimeException{
    private static  final long serialVersionUID = 1L;

    public ObjectNotFoundException(String msg) {
        super(msg);
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
