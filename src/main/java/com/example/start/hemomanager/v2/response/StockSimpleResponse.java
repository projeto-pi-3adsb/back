package com.example.start.hemomanager.v2.response;

public class StockSimpleResponse {
    private Integer id;
    private String bloodType;

    public StockSimpleResponse(int id, String bloodType) {
        this.id = id;
        this.bloodType = bloodType;
    }

    public int getUuid() {
        return id;
    }

    public String getName() {
        return bloodType;
    }
}
