package com.example.start.hemomanager.v2.controller;

import com.example.start.hemomanager.v2.domain.Donor;
import com.example.start.hemomanager.v2.domain.dto.DonorDTO;
import com.example.start.hemomanager.v2.domain.dto.InputValidation;
import com.example.start.hemomanager.v2.domain.dto.LoginDTO;
import com.example.start.hemomanager.v2.repository.DonorRepository;
import com.example.start.hemomanager.v2.repository.HemocenterRepository;
import com.example.start.hemomanager.v2.repository.ScheduleHemocenterRepository;
import com.example.start.hemomanager.v2.repository.ScheduleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController @RequestMapping("/donor")
public class DonorController {
    @Autowired private DonorRepository donorRepository;
    @Autowired private ScheduleHemocenterRepository scheduleHemocenterRepository;
    @Autowired private ScheduleRepository scheduleRepository;
    @Autowired private HemocenterRepository hemocenterRepository;
    private InputValidation validation;
    List<Donor> donors = new ArrayList<>();

    @PostMapping
    public ResponseEntity<Donor> createDonor(@RequestBody @Valid DonorDTO donorDTO) {
        if (donorRepository.existsByEmailAndCpf(donorDTO.getEmail(), donorDTO.getCpf())) throw new ResponseStatusException(HttpStatus.CONFLICT, "Email ou CPF já cadastrados.");

        Donor donor = new Donor();
        BeanUtils.copyProperties(donorDTO, donor);

        Donor saved = donorRepository.save(donor);
        return ResponseEntity.status(201).body(saved);
    }

    @PostMapping("/current")
    public ResponseEntity<Donor> loginDonor(@RequestBody LoginDTO donorDTO) {
        Donor donor = donorRepository.findByEmailAndPassword(donorDTO.getEmail(), donorDTO.getPassword());
        if (donor == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");

        return ResponseEntity.status(200).body(donor);
    }

    // método para os testes
    @PostMapping("/") @ResponseStatus(HttpStatus.CREATED)
    public Donor insertDonor(@RequestBody Donor donor) {
        return donorRepository.save(donor);
    }

    @GetMapping
    public ResponseEntity<List<Donor>> getAllDonors() {
        List<Donor> donorList = donorRepository.findAll();
        if (donorList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhum usuário cadastrado.");
        }
        return ResponseEntity.status(200).body(donorList);
    }

    @GetMapping("/gender/male")
    public ResponseEntity<Long> qttyMaleDonors() {
        Long counter = donorRepository.countBySexMale();
        if (counter == 0) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhum usuário do sexo informado foi encontrado.");

        return ResponseEntity.status(200).body(counter);
    }

    @GetMapping("/gender/female")
    public ResponseEntity<Long> qttyFemaleDonors() {
        Long counter = donorRepository.countBySexFemale();
        if (counter == 0) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhum usuário do sexo informado foi encontrado.");

        return ResponseEntity.status(200).body(counter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Donor> updateDonor(@PathVariable int id, @RequestBody Donor donorDTO) {
        if (!donorRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");

        donorDTO.setId(id);
        donorRepository.save(donorDTO);
        return ResponseEntity.status(200).body(donorDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donor> findDonor(@PathVariable int id) {
        if (!donorRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");

        Donor donor = donorRepository.findById(id);
        return ResponseEntity.status(200).body(donor);
    }
}
