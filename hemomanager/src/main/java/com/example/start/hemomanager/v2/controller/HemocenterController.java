package com.example.start.hemomanager.v2.controller;

import com.example.start.hemomanager.v2.domain.Hemocenter;
import com.example.start.hemomanager.v2.domain.Officer;
import com.example.start.hemomanager.v2.repository.HemocenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public List<Hemocenter> getAllHemocenters() {
        return hemocenterRepository.findAll();
    }

    public void cadastrarOfficer(Officer admin, Officer officer) {
        // return (admin.isAdmin()) ? officersList.add(officer) : "Usuário ${admin.getName()} não pode inserir ou alterar outros usuários.";

        System.out.println("funcionário cadastrado");
    };

    public void removerOfficer(Officer admin, Officer officer) {
        // return (admin.isAdmin()) ? officersList.remove(officer) : "Usuário ${admin.getName()} não pode alterar outros usuários.";
        System.out.println("funcionário deletado");
    };

    public void promoverOfficer(Officer admin, Officer officer) {
        // return (admin.isAdmin()) ? officer.setAdmin(true) : "Usuário ${admin.getName()} não pode alterar outros usuários.";
        System.out.println("funcionário promovido");
    };
}
