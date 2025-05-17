package com.example.ProductApplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CategoryListIsEmptyException extends RuntimeException{
    public CategoryListIsEmptyException(String message){
        super(message);
    }
}
