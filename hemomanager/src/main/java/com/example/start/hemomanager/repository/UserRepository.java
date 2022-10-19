package com.example.start.hemomanager.repository;

import com.example.start.hemomanager.shared.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByEmailAndPassword(String email, String password);
}
