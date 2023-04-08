package com.project.authentication.exception;

import lombok.Getter;

public class CannotCreateUserException extends RuntimeException{
    @Getter
    public String message;
    public CannotCreateUserException(String message) {
        this.message = message;
    }

}
