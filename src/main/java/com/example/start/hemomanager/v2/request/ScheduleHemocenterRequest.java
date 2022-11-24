package com.example.start.hemomanager.v2.request;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleHemocenterRequest {

    private int hemocenterId;
    private LocalDate scheduledDate;
    private LocalTime scheduledTime;

    public int getHemocenterId() {
        return hemocenterId;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public LocalTime getScheduledTime() {
        return scheduledTime;
    }
}
