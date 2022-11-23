package com.example.start.hemomanager.v2.dto;

import com.example.start.hemomanager.v2.domain.Hemocenter;

import java.time.LocalTime;
import java.util.List;

public class HemocenterSignInDTO extends Hemocenter {
    public HemocenterSignInDTO() {
    }

    public HemocenterSignInDTO(String name, String email, String password, String cnpj, String zipCode, String zipNumber, LocalTime startOperation, LocalTime endOperation, int qttySimultServices) {
        super(name, email, password, cnpj, zipCode, zipNumber, startOperation, endOperation, qttySimultServices);
    }
}
