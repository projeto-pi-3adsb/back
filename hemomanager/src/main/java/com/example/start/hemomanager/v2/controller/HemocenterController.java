package com.example.start.hemomanager.v2.controller;

import com.example.start.hemomanager.v2.domain.Hemocenter;
import com.example.start.hemomanager.v2.repository.HemocenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController @RequestMapping("/hemocenters")
public class HemocenterController {
    @Autowired private final HemocenterRepository hemocenterRepository;

    public HemocenterController(HemocenterRepository hemocenterRepository) {
        this.hemocenterRepository = hemocenterRepository;
    }

    @PostMapping("/") @ResponseStatus(HttpStatus.CREATED)
    public Hemocenter insertHemocenter(@RequestBody @Valid Hemocenter hemocenter) {
        return hemocenterRepository.save(hemocenter);
    }

    @GetMapping("/")
    public Iterable<Hemocenter> getAllHemocenters() {
        return hemocenterRepository.findAll();
    }
}
