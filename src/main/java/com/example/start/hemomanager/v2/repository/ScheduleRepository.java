package com.example.start.hemomanager.v2.repository;

import com.example.start.hemomanager.v2.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    @Query("SELECT new com.example.start.hemomanager.v2.domain.Schedule(s.*) FROM schedule WHERE scheduled_donor_id = ?1")
    Schedule findByDonorId(int id);

    @Query("SELECT new com.example.start.hemomanager.v2.domain.Schedule(s.*) FROM schedule WHERE scheduled_hemocenter_id = ?1")
    Schedule findByHemocenterId(int id);
}
