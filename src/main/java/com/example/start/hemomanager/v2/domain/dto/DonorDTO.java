package com.example.start.hemomanager.v2.domain.dto;

import java.time.LocalDate;

public class DonorDTO {
    private String name;
    private String email;
    private String password;
    private String cpf;
    private LocalDate birthDate;
    private String sex;
    private String phone;
    private boolean validDonor;

    public DonorDTO() {
    }

    public DonorDTO(
            String name,
            String email,
            String password,
            String cpf,
            LocalDate birthDate,
            String sex,
            String phone,
            boolean validDonor
    ) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.sex = sex;
        this.phone = phone;
        this.validDonor = validDonor;
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

    public String getCpf() {
        return cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getSex() {
        return sex;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isValidDonor() {
        return validDonor;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
