package com.example.start.hemomanager.controller;

import com.example.start.hemomanager.repository.OfficerRepository;
import com.example.start.hemomanager.repository.DonorRepository;
import com.example.start.hemomanager.service.LoginUserService;
import com.example.start.hemomanager.service.SignUserService;
import com.example.start.hemomanager.shared.user.User;
import com.example.start.hemomanager.shared.user.donor.Donor;
import com.example.start.hemomanager.shared.user.hemocenter.officer.Officer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired private DonorRepository donorRepository;
    @Autowired private OfficerRepository officerRepository;

    List<Donor> userList = donorRepository.findAll();
    List<Officer> officerList = officerRepository.findAll();

    SignUserService signUserService = new SignUserService();
    LoginUserService loginUserService = new LoginUserService();
    // the class LoginUserService will be used after the implementation of DAOs.

    // General registered users management
    @GetMapping("/listUsers")
    public ResponseEntity<List<Donor>> getAllUsers() {
        return userList.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(userList);
    }

    @DeleteMapping("/logout/{email}")
    public String logoutUser(@PathVariable String email) {
        return this.signUserService.logoutUser(email);
    }

    // Donor management
    @PostMapping("/donor/signup")
    public Donor insertDonor(@RequestBody Donor donor) {
        return donorRepository.save(donor);
    }

    @PostMapping("/donor/signin/{email}/{password}")
    public User loginDonor(@PathVariable String email, @PathVariable String password) {
        return this.signUserService.loginDonor(email, password);
    }

    // Officer management
    @PostMapping("/officer/manager/signup")
    public Officer insertManager(@RequestBody Officer manager) {
        return officerRepository.save(manager);
    }

    @PostMapping("/officer/nurse/signup")
    public Officer insertNurse(@RequestBody Officer nurse) {
        return officerRepository.save(nurse);
    }

    @PostMapping("/officer/receptionist/signup")
    public Officer insertReceptionist(@RequestBody Officer receptionist) {
        return officerRepository.save(receptionist);
    }

    @PostMapping("/officer/signin/{email}/{password}")
    public Officer loginOfficer(@PathVariable String email, @PathVariable String password) {
        return this.signUserService.loginOfficer(email, password);
    }

    @GetMapping("/listOfficers")
    public ResponseEntity<List<Officer>> getAllOfficers() {
        return officerList.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(officerList);
    }

    @GetMapping("/listAdminOfficers")
    public Officer getAllAuthorizedStockOfficers() {
        return this.signUserService.getAllAuthorizedStockOfficers();
    }
}
