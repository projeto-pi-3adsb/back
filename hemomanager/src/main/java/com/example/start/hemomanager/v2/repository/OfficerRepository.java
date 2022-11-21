package com.example.start.hemomanager.v2.repository;

import com.example.start.hemomanager.v2.domain.Officer;
import com.example.start.hemomanager.v2.response.OfficerSimpleResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfficerRepository extends JpaRepository<Officer, Integer> {
    @Query("SELECT new com.example.start.hemomanager.v2.response.OfficerSimpleResponse (o.uuid, o.name) FROM Officer o WHERE o.admin=true")
    List<OfficerSimpleResponse> findAllAdmins();
}
