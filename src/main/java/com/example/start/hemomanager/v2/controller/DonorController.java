package com.example.start.hemomanager.v2.controller;

import com.example.start.hemomanager.v2.domain.Donor;
import com.example.start.hemomanager.v2.domain.Donor;
import com.example.start.hemomanager.v2.dto.DonorSignInDTO;
import com.example.start.hemomanager.v2.dto.LoginDTO;
import com.example.start.hemomanager.v2.repository.DonorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController @RequestMapping("/donors")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DonorController {
    @Autowired private DonorRepository donorRepository;
    List<Donor> donors = new ArrayList<>();

    @PostMapping
    public ResponseEntity signIn(@RequestBody DonorSignInDTO donorDTO) {
        if (donorRepository.existsByEmailAndCpf(
                donorDTO.getEmail(),
                donorDTO.getCpf())
        ) return ResponseEntity.status(422).body("E-mail ou CNPJ já cadastrados.");

        Donor donor = new Donor();
        BeanUtils.copyProperties(donorDTO, donor);

        Donor saved = donorRepository.save(donor);
        return ResponseEntity.status(200).body(saved);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO donorDTO) {
        Donor donor = donorRepository.findByEmailAndPassword(
                donorDTO.getEmail(),
                donorDTO.getPassword());
        if (donor == null) return ResponseEntity.status(404).build();

        return ResponseEntity.status(200).build();
    }

    @PostMapping("/") @ResponseStatus(HttpStatus.CREATED)
    public Donor insertDonor(@RequestBody @Valid Donor donor) {
        return donorRepository.save(donor);
    }

    @GetMapping("/")
    public Iterable<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

//    @GetMapping("/gender")
//    public int qttyDonorsBySex() {
//        return donorRepository.countBySex();
//    }

    // Donor management
//    @PostMapping("/{email}/{password}")
//    public Donor loginDonor(@PathVariable String email, @PathVariable String password) {
//        for (Donor currentDonor : donors) {
//            if (currentDonor.authenticateDonor(email, password)) {
//                return currentDonor;
//            }
//        }
//        return null;
//    }
}
