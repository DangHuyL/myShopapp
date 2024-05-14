package com.example.myshopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myshopapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByPhoneNumber(String phoneNumber);
}
