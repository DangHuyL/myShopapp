package com.example.myshopapp.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlreadyExistException extends RuntimeException {
    private Status status;

    public AlreadyExistException(String message, Status status) {
        super(message);
        this.status = status;
    }
}
