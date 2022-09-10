package com.example.start.hemomanager.controllers.user;

import java.util.UUID;

public abstract class User {
    private UUID uuid;
    private String name;
    private String cpfCnpj;
    private String email;
    private String password;
    private boolean logged;

    public User(UUID uuid, String name, String cpfCnpj, String email, String password, boolean logged) {
        this.uuid = uuid;
        this.name = name;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.password = password;
        this.logged = logged;
    }

    public UUID getUuid() {
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
    };

    public abstract boolean doLogin();
    public abstract boolean doLogout();
}
