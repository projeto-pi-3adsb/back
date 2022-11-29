package com.example.start.hemomanager.v2.controller;

import com.example.start.hemomanager.v2.domain.*;
import com.example.start.hemomanager.v2.domain.Donor;
import com.example.start.hemomanager.v2.dto.DonorSignInDTO;
import com.example.start.hemomanager.v2.dto.InputValidation;
import com.example.start.hemomanager.v2.dto.LoginDTO;
import com.example.start.hemomanager.v2.repository.DonorRepository;
import com.example.start.hemomanager.v2.repository.HemocenterRepository;
import com.example.start.hemomanager.v2.repository.ScheduleHemocenterRepository;
import com.example.start.hemomanager.v2.repository.ScheduleRepository;
import com.example.start.hemomanager.v2.request.ScheduleRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController @RequestMapping("/donor") @CrossOrigin(origins = "*", allowedHeaders = "*")
public class DonorController {
    @Autowired private DonorRepository donorRepository;
    @Autowired private ScheduleHemocenterRepository scheduleHemocenterRepository;
    @Autowired private ScheduleRepository scheduleRepository;
    @Autowired private HemocenterRepository hemocenterRepository;
    private InputValidation validation;
    List<Donor> donors = new ArrayList<>();

    @PostMapping
    public ResponseEntity signIn(@RequestBody DonorSignInDTO donorDTO) {
        if (donorRepository.existsByEmailAndCpf(
            donorDTO.getEmail(), donorDTO.getCpf())
        ) return ResponseEntity.status(422).body("E-mail ou CNPJ já cadastrados.");

        Donor donor = new Donor();
        BeanUtils.copyProperties(donorDTO, donor);

        Donor saved = donorRepository.save(donor);
        return ResponseEntity.status(201).body(saved);
    }

    @PostMapping("/current")
    public ResponseEntity loginWithReturn(@RequestBody LoginDTO donorDTO) {
        Donor donor = donorRepository.findByEmailAndPassword(
            donorDTO.getEmail(), donorDTO.getPassword());
        if (donor == null) return ResponseEntity.status(404).build();

        return ResponseEntity.status(200).body(donor);
    }

    @PostMapping("/") @ResponseStatus(HttpStatus.CREATED)
    public Donor insertDonor(@RequestBody @Valid Donor donor) {
        return donorRepository.save(donor);
    }

    @GetMapping
    public Iterable<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    @GetMapping("/gender/male")
    public Long qttyMaleDonors() {
        return donorRepository.countBySexMale();
    }

    @GetMapping("/gender/female")
    public Long qttyFemaleDonors() {
        return donorRepository.countBySexFemale();
    }

    @PostMapping("/schedule")
    public Schedule insertSchedule(@RequestBody @Valid ScheduleRequest scheduleRequest){
        Optional<ScheduleHemocenter> scheduleHemocenterOptional =  scheduleHemocenterRepository.findById(scheduleRequest.getScheduleHemocenterId());
        Donor donorOptional = donorRepository.findById(scheduleRequest.getDonorId());
        Hemocenter hemocenterOptional = hemocenterRepository.findById(scheduleRequest.getHemocenterId());
        ScheduleHemocenter scheduleHemocenter = scheduleHemocenterOptional.get();

        Schedule schedule = new Schedule(donorOptional,hemocenterOptional,scheduleHemocenter);
        return scheduleRepository.save(schedule);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Donor> updateDonor(@PathVariable int id, @RequestBody Donor donorDTO) {
        if (donorRepository.existsById(id)) {
            donorDTO.setId(id);
            donorRepository.save(donorDTO);
            return ResponseEntity.status(200).body(donorDTO);
        }
        return ResponseEntity.status(404).build();
    }
}
