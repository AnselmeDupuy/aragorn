package com.example.aragorn.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.example.aragorn.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUsername(String username);
}
