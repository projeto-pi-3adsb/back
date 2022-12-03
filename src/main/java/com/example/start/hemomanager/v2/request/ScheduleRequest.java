package com.example.start.hemomanager.v2.request;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleRequest {

    private int hemocenterId;
    private Long donorId;
    private int scheduleHemocenterId;

    public int getHemocenterId() {
        return hemocenterId;
    }

    public void setHemocenterId(int hemocenterId) {
        this.hemocenterId = hemocenterId;
    }

    public Long getDonorId() {
        return donorId;
    }

    public void setDonorId(Long donorId) {
        this.donorId = donorId;
    }

    public int getScheduleHemocenterId() {
        return scheduleHemocenterId;
    }

    public void setScheduleHemocenterId(int scheduleHemocenterId) {
        this.scheduleHemocenterId = scheduleHemocenterId;
    }
}
