package com.example.start.hemomanager.v2.repository;

import com.example.start.hemomanager.v2.domain.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonorRepository extends JpaRepository<Donor, Integer> {
    List<Donor> findByEmailAndPassword(String email, String password);
}
