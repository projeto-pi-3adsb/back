package com.example.start.hemomanager.repository;

import com.example.start.hemomanager.shared.user.hemocenter.officer.Officer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OfficerRepository extends JpaRepository<Officer, UUID> {
    List<Officer> findByEmailAndPassword(String email, String password);

    List<Officer> findAll();
}
