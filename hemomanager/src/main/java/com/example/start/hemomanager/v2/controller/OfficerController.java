package com.example.start.hemomanager.v2.controller;

import com.example.start.hemomanager.v2.domain.Officer;
import com.example.start.hemomanager.v2.repository.OfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController @RequestMapping("/officers")
public class OfficerController {
    @Autowired private final OfficerRepository officerRepository;

    public OfficerController(OfficerRepository officerRepository) {
        this.officerRepository = officerRepository;
    }

    @PostMapping("/") @ResponseStatus(HttpStatus.CREATED)
    public Officer insertOfficer(@RequestBody @Valid Officer officer) {
        return officerRepository.save(officer);
    }

    @GetMapping("/")
    public Iterable<Officer> getAllOfficers() {
        return officerRepository.findAll();
    }
}
