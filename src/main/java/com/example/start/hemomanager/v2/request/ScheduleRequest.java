package com.example.start.hemomanager.v2.request;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleRequest {

    private int hemocenterId;
    private int donorId;
    private int scheduleHemocenterId;

    public int getHemocenterId() {
        return hemocenterId;
    }

    public void setHemocenterId(int hemocenterId) {
        this.hemocenterId = hemocenterId;
    }

    public int getDonorId() {
        return donorId;
    }

    public void setDonorId(int donorId) {
        this.donorId = donorId;
    }

    public int getScheduleHemocenterId() {
        return scheduleHemocenterId;
    }

    public void setScheduleHemocenterId(int scheduleHemocenterId) {
        this.scheduleHemocenterId = scheduleHemocenterId;
    }
}
