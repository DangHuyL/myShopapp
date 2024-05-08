package com.example.myshopapp.repository;

import com.example.myshopapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findById (String roleId);
}
