package com.example.start.hemomanager.v2.dto;

import com.example.start.hemomanager.v2.domain.Stock;

import java.time.LocalDate;

public class StockDTO extends Stock {
    public StockDTO() {
    }

    public StockDTO(LocalDate insertDate, String bloodType, LocalDate collectionDate) {
        super(insertDate, bloodType, collectionDate);
    }
}
