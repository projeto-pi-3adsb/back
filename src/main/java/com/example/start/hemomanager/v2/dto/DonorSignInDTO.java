package com.example.start.hemomanager.v2.dto;

import com.example.start.hemomanager.v2.domain.Donor;

import java.time.LocalDate;

public class DonorSignInDTO extends Donor {
    public DonorSignInDTO() {
    }

    public DonorSignInDTO(String name, String email, String password, String cpf, LocalDate birthDate, String sex, String phone, boolean validDonor) {
        super(name, email, password, cpf, birthDate, sex, phone, validDonor);
    }
}
