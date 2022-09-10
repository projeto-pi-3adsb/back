package com.example.start.hemomanager.controllers.user.hemocenter;
import com.example.start.hemomanager.controllers.user.hemocenter.officer.Officer;

import java.time.LocalTime;
import java.util.List;

public class Hemocenter {
    private String name;
    private String zipCode;
    private String zipNumber;
    private LocalTime startOperation;
    private LocalTime endOperation;
    private int qttySimultServices;
    private List<Officer> officersList;

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
