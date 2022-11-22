package com.example.start.hemomanager.v2.repository;

import com.example.start.hemomanager.v2.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
}
