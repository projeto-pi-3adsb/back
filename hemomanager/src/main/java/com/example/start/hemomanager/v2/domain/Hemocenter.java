package com.example.start.hemomanager.v2.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Entity
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

    public Hemocenter() {
    }

    public Hemocenter(
            Integer uuid,
            String name,
            String zipCode,
            String zipNumber,
            LocalTime startOperation,
            LocalTime endOperation,
            int qttySimultServices
    ) {
        this.uuid = uuid;
        this.name = name;
        this.zipCode = zipCode;
        this.zipNumber = zipNumber;
        this.startOperation = startOperation;
        this.endOperation = endOperation;
        this.qttySimultServices = qttySimultServices;
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZipNumber() {
        return zipNumber;
    }

    public void setZipNumber(String zipNumber) {
        this.zipNumber = zipNumber;
    }

    public LocalTime getStartOperation() {
        return startOperation;
    }

    public void setStartOperation(LocalTime startOperation) {
        this.startOperation = startOperation;
    }

    public LocalTime getEndOperation() {
        return endOperation;
    }

    public void setEndOperation(LocalTime endOperation) {
        this.endOperation = endOperation;
    }

    public int getQttySimultServices() {
        return qttySimultServices;
    }

    public void setQttySimultServices(int qttySimultServices) {
        this.qttySimultServices = qttySimultServices;
    }
}
