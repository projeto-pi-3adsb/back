package com.example.start.hemomanager.shared.enumerators;

public enum BloodType {
    APOS("A+"),
    ANEG("A-"),
    BPOS("B+"),
    BNEG("B-"),
    ABPOS("AB+"),
    ABNEG("AB-"),
    OPOS("O+"),
    ONEG("O-"),
    UNDEFINED("ND");

    private String type;
    BloodType(String type) {
        this.type = type;
    }
}
