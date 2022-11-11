package com.spring.ecommerce.Exceptions;

public class IdAlreadyAllocatedException extends RuntimeException {
    public IdAlreadyAllocatedException(String message) {
        super(message);
    }
}
