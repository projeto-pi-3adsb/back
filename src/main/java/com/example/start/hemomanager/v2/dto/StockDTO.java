package com.example.start.hemomanager.v2.dto;

import com.example.start.hemomanager.v2.domain.Hemocenter;
import com.example.start.hemomanager.v2.domain.Stock;

import java.time.LocalDate;

public class StockDTO {
    private String bloodType;
    private LocalDate collectionDate;

    public StockDTO() {
    }

    public StockDTO(String bloodType, LocalDate collectionDate) {
        this.bloodType = bloodType;
        this.collectionDate = collectionDate;
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

    public String getBloodType() {
        return bloodType;
    }

    public LocalDate getCollectionDate() {
        return collectionDate;
    }
}
