package com.example.myshopapp.exception;

import java.util.List;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.myshopapp.dto.response.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<List<String>> handlingMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> messages = e.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messages);
    }

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<String> handlingRuntimeException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(value = AlreadyExistException.class)
    ResponseEntity<ApiResponse> handlingAlreadyExistException(AlreadyExistException e) {
        ApiResponse apiResponse = ApiResponse.builder()
                .status(Status.ALREADY_EXISTS.getValue())
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(e.getStatus().getHttpStatusCode()).body(apiResponse);
    }

    @ExceptionHandler(value = NotFoundException.class)
    ResponseEntity<ApiResponse> handlingNotFoundException(NotFoundException e) {
        ApiResponse apiResponse = ApiResponse.builder()
                .status(Status.NOT_FOUND.getValue())
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(e.getStatus().getHttpStatusCode()).body(apiResponse);
    }

    @ExceptionHandler(value = PermissionDenyException.class)
    ResponseEntity<ApiResponse> handlingPermissionDenyExceptionException(PermissionDenyException e) {
        ApiResponse apiResponse = ApiResponse.builder()
                .status(Status.PERMISSION_DENIED.getValue())
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(e.getStatus().getHttpStatusCode()).body(apiResponse);
    }
}
