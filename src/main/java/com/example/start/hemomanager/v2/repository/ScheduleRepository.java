package com.example.start.hemomanager.v2.repository;

import com.example.start.hemomanager.v2.domain.Schedule;
import com.example.start.hemomanager.v2.response.ScheduleResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    @Query("SELECT new com.example.start.hemomanager.v2.domain.Schedule(s.uuid, s.scheduledDonor, s.hemocenter, s.scheduleHemocenter) FROM Schedule s WHERE scheduled_donor_id = ?1")
    List<Schedule> findByDonorId(int id);

    List<Schedule> findAllByHemocenter_Uuid(Integer uuid);

    @Query("SELECT new com.example.start.hemomanager.v2.response.ScheduleResponse(hm.uuid, sch.uuid, hm.name, sch.scheduledTime, sch.scheduledDate) " +
            "FROM ScheduleHemocenter sch INNER JOIN sch.hemocenter hm where hm.uuid =:id")
    List<ScheduleResponse> findSchedulesByHemocenterId(@Param("id") int id);

    @Modifying @Query("DELETE FROM Schedule WHERE uuid = ?1")
    void deleteReferenceByUuid(Integer uuid);
}
