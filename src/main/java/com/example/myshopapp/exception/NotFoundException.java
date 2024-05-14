package com.example.myshopapp.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends RuntimeException {
    private Status status;

    public NotFoundException(String message, Status status) {
        super(message);
        this.status = status;
    }
}
