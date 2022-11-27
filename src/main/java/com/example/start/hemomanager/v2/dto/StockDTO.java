package com.example.start.hemomanager.v2.dto;

import com.example.start.hemomanager.v2.domain.Hemocenter;
import com.example.start.hemomanager.v2.domain.Stock;

import java.time.LocalDate;

public class StockDTO {
    private String bloodType;
    private LocalDate collectionDate;
    private Integer hemocenter;

    public StockDTO() {
    }

    public StockDTO(String bloodType, LocalDate collectionDate, Integer hemocenter) {
        this.bloodType = bloodType;
        this.collectionDate = collectionDate;
        this.hemocenter = hemocenter;
    }

    public boolean validateBloodType(String bloodType) {
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

    public String getBloodType() {
        return bloodType;
    }

    public LocalDate getCollectionDate() {
        return collectionDate;
    }

    public Integer getHemocenter() {
        return hemocenter;
    }
}
