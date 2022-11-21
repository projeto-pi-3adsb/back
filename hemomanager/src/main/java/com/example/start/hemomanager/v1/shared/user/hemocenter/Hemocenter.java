package com.example.start.hemomanager.v1.shared.user.hemocenter;
import com.example.start.hemomanager.v1.shared.user.hemocenter.officer.Officer;
import org.hibernate.id.GUIDGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.List;

//@Entity
public class Hemocenter {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "uuid")
    private Integer uuid;

    @NotBlank @Column(name = "nome")
    private String name;

    @NotBlank @Column(name = "cep")
    private String zipCode;

    @NotBlank @Column(name = "numeroHemocentro")
    private String zipNumber;

    @NotNull @Column(name = "inicioDaOperacao")
    private LocalTime startOperation;

    @NotNull @Column(name = "fimDaOperacao")
    private LocalTime endOperation;

    @NotNull @Column(name = "quantidadeAtendimentosSimultaneos")
    private int qttySimultServices;

//    private List<Officer> officersList;

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
