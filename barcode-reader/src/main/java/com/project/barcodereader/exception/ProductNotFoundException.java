package com.project.barcodereader.exception;

import lombok.Getter;

public class ProductNotFoundException extends RuntimeException {
    @Getter
    private String message;
    public ProductNotFoundException(String message) {
        this.message = message;
    }
}
