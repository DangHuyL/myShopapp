package com.example.myshopapp.service;

import com.example.myshopapp.config.MapperConfig;
import com.example.myshopapp.dto.request.UserRequest;
import com.example.myshopapp.dto.response.UserResponse;
import com.example.myshopapp.exception.DataAlreadyExistException;
import com.example.myshopapp.exception.DataNotFoundException;
import com.example.myshopapp.exception.PermissionDenyException;
import com.example.myshopapp.model.Role;
import com.example.myshopapp.model.User;
import com.example.myshopapp.repository.RoleRepository;
import com.example.myshopapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        if(userRepository.existsByPhoneNumber(userRequest.getPhoneNumber())) {
            throw new DataAlreadyExistException("Phone number already exist");
        }

        Role role = roleRepository.findById(userRequest.getRoleId())
                .orElseThrow(() -> new DataNotFoundException("Role not found"));

        if(role.getName().toUpperCase().equals(Role.ADMIN)) {
            throw new PermissionDenyException("Can not register an admin account");
        }

        User user = modelMapper.map(userRequest, User.class);

        if(userRequest.getFacebookAccountId() == 0 && userRequest.getGoogleAccountId() == 0) {
            user.setPassword(passwordEncoder.encode(userRequest.getPhoneNumber()));
        }

        return userRepository.save(user);
    }
}
