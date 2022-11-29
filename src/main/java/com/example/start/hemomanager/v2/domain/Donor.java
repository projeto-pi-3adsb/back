package com.example.start.hemomanager.v2.domain;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Entity
public class Donor extends User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank @CPF(message = "cpf inválido")
    private String cpf;

    @NotNull @Past private LocalDate birthDate;

    @NotBlank private String sex;

    @NotBlank private String phone;

    @NotNull private boolean validDonor;

    public Donor() {
        super();
    }

    public Donor(
        String name,
        String email,
        String password,
        String cpf,
        LocalDate birthDate,
        String sex,
        String phone,
        boolean validDonor
    ) {
        super(name, email, password);
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.sex = sex;
        this.phone = phone;
        this.validDonor = validDonor;
    }

    public Donor(String name, String email, Integer id, String cpf, LocalDate birthDate, String sex, String phone, boolean validDonor) {
        this.id = id;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.sex = sex;
        this.phone = phone;
        this.validDonor = validDonor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isValidDonor() {
        return validDonor;
    }

    public void setValidDonor(boolean validDonor) {
        this.validDonor = validDonor;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
