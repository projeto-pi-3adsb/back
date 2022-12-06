package com.example.start.hemomanager.v2.domain.dto;

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

    public String getBloodType() {
        return bloodType;
    }

    public LocalDate getCollectionDate() {
        return collectionDate;
    }
}
