package com.example.start.hemomanager.v2.repository;

import com.example.start.hemomanager.v2.domain.Hemocenter;
import com.example.start.hemomanager.v2.response.HemocenterSimpleResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HemocenterRepository extends JpaRepository<Hemocenter, Integer> {
    @Query("SELECT new com.example.start.hemomanager.v2.response.HemocenterSimpleResponse (h.uuid, h.name, h.zipCode, h.zipNumber) FROM Hemocenter h")
    List<HemocenterSimpleResponse> findNameAndCep();
}
