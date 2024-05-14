package com.example.myshopapp.controller;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.example.myshopapp.dto.request.UserRequest;
import com.example.myshopapp.dto.response.ApiResponse;
import com.example.myshopapp.dto.response.UserResponse;
import com.example.myshopapp.exception.Status;
import com.example.myshopapp.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ApiResponse<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.createUser(userRequest);
        return ApiResponse.<UserResponse>builder()
                .status(Status.OK.getValue())
                .results(userResponse)
                .build();
    }
}
