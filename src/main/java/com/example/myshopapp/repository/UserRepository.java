package com.example.myshopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myshopapp.model.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, String> {
    Boolean existsByPhoneNumber(String phoneNumber);
}
