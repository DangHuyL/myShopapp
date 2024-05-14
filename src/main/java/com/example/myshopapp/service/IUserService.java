package com.example.myshopapp.service;

import com.example.myshopapp.dto.request.UserRequest;
import com.example.myshopapp.dto.response.UserResponse;

public interface IUserService {
    UserResponse createUser(UserRequest userRequest);
}
