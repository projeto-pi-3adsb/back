package com.example.start.hemomanager.v2.service;

import com.example.start.hemomanager.v2.domain.Donor;
import com.example.start.hemomanager.v2.domain.Hemocenter;
import com.example.start.hemomanager.v2.domain.Schedule;
import com.example.start.hemomanager.v2.domain.ScheduleHemocenter;
import com.example.start.hemomanager.v2.repository.DonorRepository;
import com.example.start.hemomanager.v2.repository.HemocenterRepository;
import com.example.start.hemomanager.v2.repository.ScheduleHemocenterRepository;
import com.example.start.hemomanager.v2.repository.ScheduleRepository;
import com.example.start.hemomanager.v2.request.ScheduleHemocenterRequest;
import com.example.start.hemomanager.v2.request.ScheduleRequest;
import com.example.start.hemomanager.v2.response.ScheduleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {
    @Autowired DonorRepository donorRepository;

    @Autowired
    ScheduleHemocenterRepository scheduleHemocenterRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired HemocenterRepository hemocenterRepository;

    @Transactional
    public ResponseEntity<Iterable<Schedule>> getAllSchedules() {
        List<Schedule> scheduleList = scheduleRepository.findAll();
        if (scheduleList.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhum agendamento cadastrado.");

        return ResponseEntity.status(200).body(scheduleList);
    }

    @Transactional
    public ResponseEntity<List<Schedule>> findDonorById(@PathVariable Long id) {
        if (donorRepository.findById(id).isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doador inválido ou inexistente.");

        List<Schedule> schedule = scheduleRepository.findByDonorId(id);
        return ResponseEntity.status(200).body(schedule);
    }

    @Transactional
    public ResponseEntity<List<ScheduleResponse>> getHoursByHemocenter(@PathVariable int id) {
        if (hemocenterRepository.findById(id) == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hemocentro não encontrado.");
        List<ScheduleResponse> requestList = scheduleRepository.findSchedulesByHemocenterId(id);

        return ResponseEntity.status(200).body(requestList);
    }

    @Transactional
    public ResponseEntity<ScheduleHemocenter> insertDateHour(@RequestBody @Valid ScheduleHemocenterRequest scheduleRequest){
        int id = scheduleRequest.getHemocenterId();
        LocalDate scheduledDate = scheduleRequest.getScheduledDate();
        LocalTime scheduledTime = scheduleRequest.getScheduledTime();
        Hemocenter hemocenter = hemocenterRepository.findById(id);

        if (hemocenter == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hemocentro inválido ou inexistente.");

        ScheduleHemocenter scheduleHemocenter = new ScheduleHemocenter(hemocenter, scheduledDate, scheduledTime);

        scheduleHemocenterRepository.save(scheduleHemocenter);
        return ResponseEntity.status(201).body(scheduleHemocenter);
    }

    @Transactional
    public ResponseEntity<Schedule> insertSchedule(@RequestBody ScheduleRequest scheduleRequest){
        int scheduledHemocenter = scheduleRequest.getHemocenterId();
        Long scheduledDonor = scheduleRequest.getDonorId();
        int scheduledRequest = scheduleRequest.getScheduleHemocenterId();

        if (scheduledHemocenter <= 0 || scheduledHemocenter > hemocenterRepository.count())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hemocentro inválido.");

        if (scheduledDonor <= 0 || scheduledDonor > donorRepository.count())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Doador inválido.");

        if (scheduleRepository.existsById(scheduledRequest))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Agendamento inválido.");

        ScheduleHemocenter scheduleHemocenter = scheduleHemocenterRepository.findById(scheduledRequest);
        Hemocenter hemocenter = hemocenterRepository.findById(scheduledHemocenter);
        Optional<Donor> donor = donorRepository.findById(scheduledDonor);

        Schedule schedule = new Schedule(donor, hemocenter, scheduleHemocenter);
        scheduleRepository.save(schedule);

        return ResponseEntity.status(201).body(schedule);
    }

    @Transactional
    public ResponseEntity<ScheduleHemocenter> findScheduleHemocenter(@PathVariable int id) {
        ScheduleHemocenter hemocenter = scheduleHemocenterRepository.findById(id);
        if (hemocenter == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hemocentro inválido ou inexistente.");

        return ResponseEntity.status(200).body(hemocenter);
    }

    @Transactional
    public ResponseEntity<List<Schedule>> findByHemocenterId(@PathVariable int id) {
        List<Schedule> scheduleList = scheduleRepository.findByHemocenterId(id);
        if (scheduleList.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Não foram encontrados agendamentos.");

        return ResponseEntity.status(200).body(scheduleList);
    }
}
