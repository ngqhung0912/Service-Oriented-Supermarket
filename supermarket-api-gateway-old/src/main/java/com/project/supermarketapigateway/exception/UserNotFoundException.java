package com.project.supermarketapigateway.exception;

import lombok.Getter;

public class UserNotFoundException extends RuntimeException{
    @Getter
    public String message;
    public UserNotFoundException(String message) {
        this.message = message;
    }
}
