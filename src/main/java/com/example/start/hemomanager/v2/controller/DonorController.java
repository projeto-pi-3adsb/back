package com.example.start.hemomanager.v2.controller;

import com.example.start.hemomanager.v2.domain.Donor;
import com.example.start.hemomanager.v2.domain.dto.DonorSignInDTO;
import com.example.start.hemomanager.v2.domain.dto.InputValidation;
import com.example.start.hemomanager.v2.domain.dto.LoginDTO;
import com.example.start.hemomanager.v2.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController @RequestMapping("/donor")
public class DonorController {
    @Autowired
    DonorService donorService;

    private InputValidation validation;

    List<Donor> donors = new ArrayList<>();

    @PostMapping
    public ResponseEntity<Donor> createDonor(@RequestBody @Valid DonorSignInDTO donorDTO) {
        return donorService.createDonor(donorDTO);
    }

    @PostMapping("/current")
    public ResponseEntity<Donor> loginDonor(@RequestBody LoginDTO donorDTO) {
        return donorService.loginDonor(donorDTO);
    }

    @GetMapping
    public ResponseEntity<List<Donor>> getAllDonors() {
        return donorService.getAllDonors();
    }

    @GetMapping("/gender/male")
    public ResponseEntity<Long> qttyMaleDonors() {
        return donorService.qttyMaleDonors();
    }

    @GetMapping("/gender/female")
    public ResponseEntity<Long> qttyFemaleDonors() {
        return donorService.qttyFemaleDonors();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Donor> updateDonor(@PathVariable Long id, @RequestBody Donor donorDTO) {
        return donorService.updateDonor(id, donorDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Donor>> findDonor(@PathVariable Long id) {
        return donorService.findDonor(id);
    }
}
