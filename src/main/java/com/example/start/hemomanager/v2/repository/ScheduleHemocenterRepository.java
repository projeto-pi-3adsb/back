package com.example.start.hemomanager.v2.repository;

import com.example.start.hemomanager.v2.domain.ScheduleHemocenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleHemocenterRepository extends JpaRepository<ScheduleHemocenter, Integer> {
    ScheduleHemocenter findById(int id);

}
