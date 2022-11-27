package com.example.start.hemomanager.v2.response;

public class StockSimpleResponse {
    private long counter;
    private String bloodType;

    public StockSimpleResponse(long counter, String bloodType) {
        this.counter = counter;
        this.bloodType = bloodType;
    }

    public long getCounter() {
        return counter;
    }

    public String getName() {
        return bloodType;
    }
}
