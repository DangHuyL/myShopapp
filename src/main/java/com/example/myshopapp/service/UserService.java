package com.example.myshopapp.service;

import jakarta.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.myshopapp.dto.request.UserRequest;
import com.example.myshopapp.dto.response.UserResponse;
import com.example.myshopapp.exception.*;
import com.example.myshopapp.model.Role;
import com.example.myshopapp.model.User;
import com.example.myshopapp.repository.RoleRepository;
import com.example.myshopapp.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        if (userRequest.getPassword() != userRequest.getRetypePassword()) {
            throw new InvalidArgumentException("User register password  not match", Status.INVALID_ARGUMENT);
        }

        if (userRepository.existsByPhoneNumber(userRequest.getPhoneNumber())) {
            throw new AlreadyExistException("Phone number already exist", Status.ALREADY_EXISTS);
        }

        Role role = roleRepository
                .findById(userRequest.getRoleId())
                .orElseThrow(() -> new NotFoundException("Role not found", Status.NOT_FOUND));

        if (role.getName().toUpperCase().equals(Role.ADMIN)) {
            throw new PermissionDenyException("Can not register an admin account", Status.PERMISSION_DENIED);
        }

        User user = modelMapper.map(userRequest, User.class);
        if (userRequest.getFacebookAccountId() == 0 && userRequest.getGoogleAccountId() == 0) {
            user.setPassword(passwordEncoder.encode(userRequest.getPhoneNumber()));
        }
        userRepository.save(user);
        return modelMapper.map(user, UserResponse.class);
    }
}
