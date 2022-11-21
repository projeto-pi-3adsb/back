package com.example.start.hemomanager.v2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Officer extends User {
    @NotBlank @Column(name = "role_officer")
    private String roleOfficer;

    @NotNull @Column(name = "admin")
    private Integer admin;

    @NotNull @Column(name = "fk_hemocenter")
    private Integer fk_hemocenter;

    public Officer() {
    }

    public Officer(
            Integer uuid,
            String name,
            String cpfCnpj,
            String email,
            String password,
            String roleOfficer,
            Integer admin,
            Integer fk_hemocenter
    ) {
        super(uuid, name, cpfCnpj, email, password);
        this.roleOfficer = roleOfficer;
        this.admin = admin;
        this.fk_hemocenter = fk_hemocenter;
    }

    public boolean authenticateOfficer(String email, String password){
        boolean auth = email.equals(super.getEmail()) && password.equals(super.getPassword());
        setLogged(auth);
        return auth;
    }

    public String getRoleOfficer() {
        return roleOfficer;
    }

    public void setRoleOfficer(String roleOfficer) {
        this.roleOfficer = roleOfficer;
    }

    public Integer isAdmin() {
        return admin;
    }

    public void setAdmin(Integer isAdmin) {
        admin = isAdmin;
    }

    public Integer getFk_hemocenter() {
        return fk_hemocenter;
    }

    public void setFk_hemocenter(Integer fk_hemocenter) {
        this.fk_hemocenter = fk_hemocenter;
    }
}
