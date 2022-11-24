package com.example.start.hemomanager.v2.repository;

import com.example.start.hemomanager.v2.domain.Hemocenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HemocenterRepository extends JpaRepository<Hemocenter, Integer> {
//    @Query("SELECT new com.example.start.hemomanager.v2.response.HemocenterSimpleResponse (h.uuid, h.name, h.zipCode, h.zipNumber) FROM Hemocenter h")
//    Iterable<HemocenterSimpleResponse> findNameAndCep();

    boolean existsByEmailAndCnpj(String email, String cnpj);

    Hemocenter findByEmailAndPassword(String email, String password);

}

