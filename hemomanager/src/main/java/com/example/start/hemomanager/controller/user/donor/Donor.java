package com.example.start.hemomanager.controllers.user.donor;
import com.example.start.hemomanager.controllers.enumerators.BloodType;
import com.example.start.hemomanager.controllers.user.User;

import java.util.Date;
import java.util.UUID;

public class Donor extends User {
    private Date birthDate;
    private String sex;
    private BloodType bloodType;
    private String phone;
    private boolean validDonor;
    private String zipCode;
    private String homeNumber;

    public Donor (UUID uuid, String name, String cpfCnpj,
                  String email, String password, boolean logged,
                  Date birthDate, String sex, BloodType bloodType,
                  String phone, boolean validDonor, String zipCode,
                  String homeNumber) {
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
