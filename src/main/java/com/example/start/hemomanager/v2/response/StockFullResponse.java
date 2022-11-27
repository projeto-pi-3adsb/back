package com.example.start.hemomanager.v2.response;

import java.time.LocalDate;

public class StockFullResponse {
    private String bloodType;
    private LocalDate collectionDate;
    private LocalDate insertDate;

    public StockFullResponse(String bloodType, LocalDate collectionDate, LocalDate insertDate) {
        this.bloodType = bloodType;
        this.collectionDate = collectionDate;
        this.insertDate = insertDate;
    }

    public String getBloodType() {
        return bloodType;
    }

    public LocalDate getCollectionDate() {
        return collectionDate;
    }

    public LocalDate getInsertDate() {
        return insertDate;
    }
}
