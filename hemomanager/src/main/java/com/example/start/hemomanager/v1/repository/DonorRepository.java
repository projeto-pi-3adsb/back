package com.example.start.hemomanager.v1.repository;

import com.example.start.hemomanager.v1.shared.user.donor.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DonorRepository extends JpaRepository<Donor, UUID> {
    List<Donor> findByEmailAndPassword(String email, String password);
}
