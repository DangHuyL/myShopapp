package com.example.myshopapp.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionDenyException extends RuntimeException {
    Status status;

    public PermissionDenyException(String message, Status status) {
        super(message);
        this.status = status;
    }
}
