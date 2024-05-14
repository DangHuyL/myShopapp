package com.example.myshopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myshopapp.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {}
