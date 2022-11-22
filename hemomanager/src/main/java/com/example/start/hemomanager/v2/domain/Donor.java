package com.example.start.hemomanager.v2.domain;

import com.example.start.hemomanager.v2.utils.enumerators.BloodType;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Donor extends User {
    @NotNull
    private LocalDate birthDate;
    @NotBlank
    private String sex;
    @NotNull
    private BloodType bloodType;
    @NotBlank
    private String phone;
    @NotNull
    private boolean validDonor;

    public Donor() {
        super();
    }

    public Donor (
            Integer uuid,
            String name,
            String cpfCnpj,
            String email,
            String password,
            boolean logged,
            LocalDate birthDate,
            String sex,
            BloodType bloodType,
            String phone,
            boolean validDonor
    ) {
        super(uuid, name, cpfCnpj, email, password);
        this.birthDate = birthDate;
        this.sex = sex;
        this.bloodType = bloodType;
        this.phone = phone;
        this.validDonor = validDonor;
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

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
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
}
