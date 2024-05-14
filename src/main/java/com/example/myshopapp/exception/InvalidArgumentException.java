package com.example.myshopapp.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidArgumentException extends RuntimeException {
    private Status status;

    public InvalidArgumentException(String message, Status status) {
        super(message);
        this.status = status;
    }
}
