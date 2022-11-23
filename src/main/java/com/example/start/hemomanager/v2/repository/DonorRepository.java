package com.example.start.hemomanager.v2.repository;

import com.example.start.hemomanager.v2.domain.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Integer> {
    Donor findByEmailAndPassword(String email, String password);

    boolean existsByEmailAndCpf(String email, String cpf);
}
