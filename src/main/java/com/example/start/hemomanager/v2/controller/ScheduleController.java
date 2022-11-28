package com.example.start.hemomanager.v2.controller;

import com.example.start.hemomanager.v2.domain.Donor;
import com.example.start.hemomanager.v2.domain.Hemocenter;
import com.example.start.hemomanager.v2.domain.Schedule;
import com.example.start.hemomanager.v2.domain.ScheduleHemocenter;
import com.example.start.hemomanager.v2.repository.DonorRepository;
import com.example.start.hemomanager.v2.repository.HemocenterRepository;
import com.example.start.hemomanager.v2.repository.ScheduleHemocenterRepository;
import com.example.start.hemomanager.v2.repository.ScheduleRepository;
import com.example.start.hemomanager.v2.request.ScheduleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController @RequestMapping("/schedules") @CrossOrigin(origins = "*", allowedHeaders = "*")
public class ScheduleController {

    @Autowired private DonorRepository donorRepository;
    @Autowired private ScheduleHemocenterRepository scheduleHemocenterRepository;
    @Autowired private ScheduleRepository scheduleRepository;
    @Autowired private HemocenterRepository hemocenterRepository;
    List<Schedule> schedules = new ArrayList<>();

    @GetMapping
    public Iterable<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @PostMapping()
    public ResponseEntity<Schedule> insertSchedule(@RequestBody @Valid ScheduleRequest scheduleRequest){
        Optional<ScheduleHemocenter> scheduleHemocenterOptional =  scheduleHemocenterRepository.findById(scheduleRequest.getScheduleHemocenterId());
        Optional<Donor> donorOptional = donorRepository.findById(scheduleRequest.getDonorId());
        Optional<Hemocenter> hemocenterOptional = hemocenterRepository.findById(scheduleRequest.getHemocenterId());

        if (scheduleHemocenterOptional.isEmpty() || donorOptional.isEmpty() || hemocenterOptional.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        ScheduleHemocenter scheduleHemocenter = scheduleHemocenterOptional.get();
        Donor donor = donorOptional.get();
        Hemocenter hemocenter = hemocenterOptional.get();

        Schedule schedule = new Schedule(donor,hemocenter,scheduleHemocenter);
        scheduleRepository.save(schedule);
        return ResponseEntity.status(201).body(schedule);
    }
}
