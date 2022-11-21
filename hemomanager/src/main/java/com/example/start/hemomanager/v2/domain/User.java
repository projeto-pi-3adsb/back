package com.example.start.hemomanager.v2.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@MappedSuperclass
public abstract class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "uuid",  columnDefinition="uniqueidentifier") @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    private Integer uuid;

    @NotBlank @Column(name = "nome")
    private String name;

    @NotBlank @CPF(message = "cpf inválido")
//    @CNPJ(message = "cnpj inválido")
    @Column(name = "cpf_cnpj")
    private String cpfCnpj;

    @NotBlank @Email(message = "email inválido") @Column(name = "email")
    private String email;

    @NotBlank @Column(name = "password")
    private String password;

    private boolean logged = false;

    public User(
            Integer uuid,
            String name,
            String cpfCnpj,
            String email,
            String password
    ) {
        this.uuid = uuid;
        this.name = name;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.password = password;
    }

    public User() {

    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }
}
