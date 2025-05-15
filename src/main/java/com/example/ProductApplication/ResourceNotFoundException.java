package com.example.ProductApplication;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String ex) {
        super(ex);
    }
}
