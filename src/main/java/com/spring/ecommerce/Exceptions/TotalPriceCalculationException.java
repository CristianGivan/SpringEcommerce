package com.spring.ecommerce.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TotalPriceCalculationException extends RuntimeException{
    public TotalPriceCalculationException(String message) {
        super(message);
    }
}
