package com.example.start.hemomanager.shared.user;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@MappedSuperclass
public abstract class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "uuid")
    private Long uuid;

    @NotBlank @Column(name = "nome")
    private String name;

    @NotBlank @CPF @CNPJ @Column(name = "cpf_cnpj")
    private String cpfCnpj;

    @NotBlank @Email @Column(name = "email")
    private String email;

    @NotBlank @Column(name = "password")
    private String password;
    private boolean logged;

    public User(Long uuid, String name, String cpfCnpj, String email, String password, boolean logged) {
        this.uuid = uuid;
        this.name = name;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.password = password;
        this.logged = logged;
    }

    public User() {

    }

    public Long getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public boolean authenticate(String email, String password){
        boolean auth = email.equals(this.email) && password.equals(this.password);
        setLogged(auth);
        return auth;
    }

    public abstract boolean doLogin();
    public abstract boolean doLogout();
}
