package com.example.start.hemomanager.v2.domain;

import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Entity
public class Hemocenter extends User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uuid;

    @NotBlank @CNPJ(message = "cnpj inválido")
    private String cnpj;

    @NotBlank private String zipCode;

    @NotBlank private String zipNumber;

    @NotNull private LocalTime startOperation;

    @NotNull private LocalTime endOperation;

    @NotNull private int qttySimultServices;

    public Hemocenter() {
    }

    public Hemocenter(String name, String email, String password, String cnpj, String zipCode, String zipNumber, LocalTime startOperation, LocalTime endOperation, int qttySimultServices) {
        super(name, email, password);
        this.cnpj = cnpj;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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
