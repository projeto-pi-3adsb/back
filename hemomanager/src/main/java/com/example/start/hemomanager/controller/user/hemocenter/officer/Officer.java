package com.example.start.hemomanager.controllers.user.hemocenter.officer;

import com.example.start.hemomanager.controllers.user.User;
import java.util.UUID;

public abstract class Officer extends User {
    private boolean admin;

    public Officer(UUID uuid, String name, String cpfCnpj, String email, String password, boolean logged, boolean admin) {
        super(uuid, name, cpfCnpj, email, password, logged);
        this.admin = admin;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
