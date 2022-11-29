package com.example.start.hemomanager.v2.controller;

import com.example.start.hemomanager.v2.domain.Donor;
import com.example.start.hemomanager.v2.domain.Hemocenter;
import com.example.start.hemomanager.v2.domain.Schedule;
import com.example.start.hemomanager.v2.domain.ScheduleHemocenter;
import com.example.start.hemomanager.v2.repository.DonorRepository;
import com.example.start.hemomanager.v2.repository.HemocenterRepository;
import com.example.start.hemomanager.v2.repository.ScheduleHemocenterRepository;
import com.example.start.hemomanager.v2.repository.ScheduleRepository;
import com.example.start.hemomanager.v2.request.DonorFinderRequest;
import com.example.start.hemomanager.v2.request.ScheduleHemocenterRequest;
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

    @GetMapping("/donor")
    public ResponseEntity<Donor> findDonorById(@RequestBody DonorFinderRequest donorFinderRequest) {
        int id = donorFinderRequest.getId();

        if (donorRepository.findById(id) == null) {
            return ResponseEntity.status(404).build();
        }
        Donor donor = donorRepository.findById(id);
        return ResponseEntity.status(200).body(donor);
    }

    @PostMapping
    public ResponseEntity<ScheduleHemocenter> insertSchedule(@RequestBody @Valid ScheduleHemocenterRequest scheduleRequest){
        Optional<ScheduleHemocenter> hemocenterOptional = scheduleHemocenterRepository.findById(scheduleRequest.getHemocenterId());
        ScheduleHemocenter hemocenter = hemocenterOptional.get();
        if (hemocenterOptional.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        ScheduleHemocenter scheduleHemocenter = new ScheduleHemocenter(hemocenter, scheduleRequest.getScheduledDate(), scheduleRequest.getScheduledTime());

        scheduleHemocenterRepository.save(scheduleHemocenter);
        return ResponseEntity.status(201).body(scheduleHemocenter);
    }
}
