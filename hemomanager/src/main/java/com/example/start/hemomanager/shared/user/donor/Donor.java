package com.example.start.hemomanager.shared.user.donor;
import com.example.start.hemomanager.shared.enumerators.BloodType;
import com.example.start.hemomanager.shared.user.User;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
public class Donor extends User {
    @NotBlank
    private LocalDate birthDate;
    @NotBlank
    private String sex;
    @NotBlank
    private BloodType bloodType;
    @NotBlank
    private String phone;
    @NotBlank
    private boolean validDonor;
    @NotBlank
    private String zipCode;
    @NotBlank
    private String homeNumber;

    public Donor() {
    }

    public Donor (Long uuid, String name, String cpfCnpj, String email, String password, boolean logged, LocalDate birthDate,
                  String sex, BloodType bloodType, String phone, boolean validDonor, String zipCode, String homeNumber) {
        super(uuid, name, cpfCnpj, email, password, logged);
        this.birthDate = birthDate;
        this.sex = sex;
        this.bloodType = bloodType;
        this.phone = phone;
        this.validDonor = validDonor;
        this.zipCode = zipCode;
        this.homeNumber = homeNumber;
    }

    @Override
    public boolean doLogin() {
        return false;
    }

    @Override
    public boolean doLogout() {
        return false;
    }
}
