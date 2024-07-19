package com.example.Token.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Token.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}