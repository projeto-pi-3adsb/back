package com.example.start.hemomanager.v2.controller;

import com.example.start.hemomanager.v2.domain.Schedule;
import com.example.start.hemomanager.v2.domain.ScheduleHemocenter;
import com.example.start.hemomanager.v2.request.ScheduleHemocenterRequest;
import com.example.start.hemomanager.v2.request.ScheduleRequest;
import com.example.start.hemomanager.v2.response.ScheduleResponse;
import com.example.start.hemomanager.v2.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<Iterable<Schedule>> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    @GetMapping("/donor/{id}")
    public ResponseEntity<List<Schedule>> findDonorById(@PathVariable Long id) {
        return scheduleService.findDonorById(id);

    }

    @GetMapping("/hemocenter/all/{id}")
    public ResponseEntity<List<ScheduleResponse>> getHoursByHemocenter(@PathVariable int id) {
        return scheduleService.getHoursByHemocenter(id);

    }

    @PostMapping("/hour")
    public ResponseEntity<ScheduleHemocenter> insertDateHour(@RequestBody @Valid ScheduleHemocenterRequest scheduleRequest){
        return scheduleService.insertDateHour(scheduleRequest);

    }

    @PostMapping
    public ResponseEntity<Schedule> insertSchedule(@RequestBody ScheduleRequest scheduleRequest){
        return scheduleService.insertSchedule(scheduleRequest);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleHemocenter> findScheduleHemocenter(@PathVariable int id) {
        return scheduleService.findScheduleHemocenter(id);

    }

    @GetMapping("/hemocenter/{id}")
    public ResponseEntity<List<Schedule>> findByHemocenterId(@PathVariable int id) {
        return scheduleService.findByHemocenterId(id);

    }
}
