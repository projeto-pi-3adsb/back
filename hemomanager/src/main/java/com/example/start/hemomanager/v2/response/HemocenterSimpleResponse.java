package com.example.start.hemomanager.v2.response;

public class HemocenterSimpleResponse {
    private Integer uuid;
    private String nome;
    private String zipCode;
    private String zipNumber;

    public HemocenterSimpleResponse(Integer uuid, String nome, String zipCode, String zipNumber) {
        this.uuid = uuid;
        this.nome = nome;
        this.zipCode = zipCode;
        this.zipNumber = zipNumber;
    }

    public Integer getUuid() {
        return uuid;
    }

    public String getNome() {
        return nome;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getZipNumber() {
        return zipNumber;
    }
}
