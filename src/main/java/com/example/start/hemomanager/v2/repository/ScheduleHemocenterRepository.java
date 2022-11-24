package com.example.start.hemomanager.v2.repository;

import com.example.start.hemomanager.v2.domain.ScheduleHemocenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleHemocenterRepository extends JpaRepository<ScheduleHemocenter, Integer> {
}
