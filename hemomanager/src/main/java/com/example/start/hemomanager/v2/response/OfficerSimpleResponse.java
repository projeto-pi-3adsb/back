package com.example.start.hemomanager.v2.response;

public class OfficerSimpleResponse {
    private Integer uuid;
    private String name;

    public OfficerSimpleResponse(int uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public int getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }
}
