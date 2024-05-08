package com.example.myshopapp.service;

import com.example.myshopapp.dto.request.UserRequest;
import com.example.myshopapp.dto.response.UserResponse;
import com.example.myshopapp.model.User;

public interface IUserService {
    UserResponse createUser(UserRequest userRequest);
}
