package com.example.start.hemomanager.v2.dto;

import com.example.start.hemomanager.v2.domain.Stock;

public class SimpleStockDTO extends Stock {
    public SimpleStockDTO() {
    }

    public SimpleStockDTO(String bloodType) {
        super(bloodType);
    }
}
