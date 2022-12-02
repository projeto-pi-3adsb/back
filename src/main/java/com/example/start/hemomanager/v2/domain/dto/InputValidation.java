package com.example.start.hemomanager.v2.domain.dto;

public class InputValidation {
    public boolean validateDonorSex(String sex) {
        switch (sex) {
            case "MALE":
            case "FEMALE":
                return true;
            default:
                return false;
        }
    }

    public boolean validateBloodType(String bloodType) {
        switch (bloodType) {
            case "A+":
            case "A-":
            case "B+":
            case "B-":
            case "AB+":
            case "AB-":
            case "O+":
            case "O-":
                return true;
            default:
                return false;
        }
    }
}