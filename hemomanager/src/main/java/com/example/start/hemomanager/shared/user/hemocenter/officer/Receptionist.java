package com.example.start.hemomanager.shared.user.hemocenter.officer;

import java.util.UUID;

public class Receptionist extends Officer {

    public Receptionist(UUID uuid, String name, String cpfCnpj, String email, String password, boolean logged, boolean admin) {
        super(uuid, name, cpfCnpj, email, password, logged, admin);
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
