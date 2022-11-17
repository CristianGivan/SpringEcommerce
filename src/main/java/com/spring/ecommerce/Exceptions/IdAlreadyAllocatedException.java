package com.spring.ecommerce.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class IdAlreadyAllocatedException extends RuntimeException {
    public IdAlreadyAllocatedException(String message) {
        super(message);
    }
}
