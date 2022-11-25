package com.example.start.hemomanager.v2.dto;

import com.example.start.hemomanager.v2.domain.Stock;

import java.time.LocalDate;

public class StockDTO extends Stock {
    public StockDTO() {
    }

    public StockDTO(String bloodType, LocalDate collectionDate) {
        super(bloodType, collectionDate);
    }

    public boolean validateBloodType(String bloodType) {
        System.out.println(bloodType);
        switch (bloodType) {
            case "APos":
            case "ANeg":
            case "BPos":
            case "BNeg":
            case "ABPos":
            case "ABNeg":
            case "OPos":
            case "ONeg":
                return true;
            default:
                return false;
        }
    }
}
