package com.example.start.hemomanager.v2.dto;

import com.example.start.hemomanager.v2.domain.Hemocenter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.List;

public class HemocenterSignInDTO {
    private String name;
    private String email;
    private String password;
    private String cnpj;
    private String zipCode;
    private String zipNumber;
    private LocalTime startOperation;
    private LocalTime endOperation;
    private int qttySimultServices;

    public HemocenterSignInDTO() {
    }

    public HemocenterSignInDTO(
        String name,
        String email,
        String password,
        String cnpj,
        String zipCode,
        String zipNumber,
        LocalTime startOperation,
        LocalTime endOperation,
        int qttySimultServices
    ) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cnpj = cnpj;
        this.zipCode = zipCode;
        this.zipNumber = zipNumber;
        this.startOperation = startOperation;
        this.endOperation = endOperation;
        this.qttySimultServices = qttySimultServices;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getZipNumber() {
        return zipNumber;
    }

    public LocalTime getStartOperation() {
        return startOperation;
    }

    public LocalTime getEndOperation() {
        return endOperation;
    }

    public int getQttySimultServices() {
        return qttySimultServices;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setZipNumber(String zipNumber) {
        this.zipNumber = zipNumber;
    }

    public void setStartOperation(LocalTime startOperation) {
        this.startOperation = startOperation;
    }

    public void setEndOperation(LocalTime endOperation) {
        this.endOperation = endOperation;
    }

    public void setQttySimultServices(int qttySimultServices) {
        this.qttySimultServices = qttySimultServices;
    }
}
