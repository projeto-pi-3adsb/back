package com.example.start.hemomanager.v1.shared.user.donor;
import com.example.start.hemomanager.v1.shared.enumerators.BloodType;
import com.example.start.hemomanager.v1.shared.user.User;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
public class Donor extends User {
    private Date birthDate;
    @NotBlank
    private String sex;
    @NotNull
    private BloodType bloodType;
    @NotBlank
    private String phone;
    @NotNull
    private boolean validDonor;
    @NotBlank
    private String zipCode;
    @NotBlank
    private String homeNumber;

    public Donor (UUID uuid, String name, String cpfCnpj, String email, String password, boolean logged, Date birthDate,
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
