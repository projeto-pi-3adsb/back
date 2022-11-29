package com.example.start.hemomanager.v2.request;

public class DonorFinderRequest {
    private Integer id;

    public DonorFinderRequest(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
