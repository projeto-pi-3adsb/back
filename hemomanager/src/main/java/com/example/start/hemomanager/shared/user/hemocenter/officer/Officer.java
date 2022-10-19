package com.example.start.hemomanager.shared.user.hemocenter.officer;
import com.example.start.hemomanager.shared.user.User;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public abstract class Officer extends User {
    private boolean admin;

    public Officer(UUID uuid, String name, String cpfCnpj, String email, String password, boolean logged, boolean admin) {
        super(uuid, name, cpfCnpj, email, password, logged);
        this.admin = admin;
    }

    public boolean authenticateOfficer(String email, String password){
        boolean auth = email.equals(super.getEmail()) && password.equals(super.getPassword());
        setLogged(auth);
        return auth;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
