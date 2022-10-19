package com.example.start.hemomanager.controller;

import com.example.start.hemomanager.service.LoginUserService;
import com.example.start.hemomanager.service.SignUserService;
import com.example.start.hemomanager.shared.user.User;
import com.example.start.hemomanager.shared.user.donor.Donor;
import com.example.start.hemomanager.shared.user.hemocenter.officer.Manager;
import com.example.start.hemomanager.shared.user.hemocenter.officer.Nurse;
import com.example.start.hemomanager.shared.user.hemocenter.officer.Officer;
import com.example.start.hemomanager.shared.user.hemocenter.officer.Receptionist;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    SignUserService signUserService = new SignUserService();
    LoginUserService loginUserService = new LoginUserService();
    // the class LoginUserService will be used after the implementation of DAOs.

    // General registered users management
    @GetMapping("/listUsers")
    public List<User> getAllUsers() {
        return this.signUserService.getAllUsers();
    }

    @DeleteMapping("/logout/{email}")
    public String logoutUser(@PathVariable String email) {
        return this.signUserService.logoutUser(email);
    }

    // Donor management
    @PostMapping("/donor/signup")
    public Donor insertDonor(@RequestBody Donor donor) {
        return this.signUserService.insertDonor(donor);
    }

    @PostMapping("/donor/signin/{email}/{password}")
    public User loginDonor(@PathVariable String email, @PathVariable String password) {
        return this.signUserService.loginDonor(email, password);
    }

    // Officer management
    @PostMapping("/officer/manager/signup")
    public Manager insertManager(@RequestBody Manager manager) {
        return this.signUserService.insertManager(manager);
    }

    @PostMapping("/officer/nurse/signup")
    public Nurse insertNurse(@RequestBody Nurse nurse) {
        return this.signUserService.insertNurse(nurse);
    }

    @PostMapping("/officer/receptionist/signup")
    public Receptionist insertReceptionist(@RequestBody Receptionist receptionist) {
        return this.signUserService.insertReceptionist(receptionist);
    }

    @PostMapping("/officer/signin/{email}/{password}")
    public Officer loginOfficer(@PathVariable String email, @PathVariable String password) {
        return this.signUserService.loginOfficer(email, password);
    }

    @GetMapping("/listOfficers")
    public List<Officer> getAllOfficers() {
        return this.signUserService.getAllOfficers();
    }

    @GetMapping("/listAdminOfficers")
    public Officer getAllAuthorizedStockOfficers() {
        return this.signUserService.getAllAuthorizedStockOfficers();
    }

//    @GetMapping("/csv")
//    public List<Donor> donorsDowloads(){
//
//    }
}
