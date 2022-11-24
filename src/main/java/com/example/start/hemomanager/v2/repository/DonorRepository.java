package com.example.start.hemomanager.v2.repository;

import com.example.start.hemomanager.v2.domain.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Integer> {
    Donor findByEmailAndPassword(String email, String password);

    boolean existsByEmailAndCpf(String email, String cpf);

    @Query(
        value = "SELECT COUNT(*) FROM donor WHERE sex like 'M%'",
        nativeQuery = true
    )
    Long countBySexMale();

    @Query(
        value = "SELECT COUNT(*) FROM donor WHERE sex like 'F%'",
        nativeQuery = true
    )
    Long countBySexFemale();
}
