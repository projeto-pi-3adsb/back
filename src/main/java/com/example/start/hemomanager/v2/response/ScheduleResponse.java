package com.example.start.hemomanager.v2.response;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleResponse {
    private int hemocenterUuid;
    private int scheduleHemocenterUuid;
    private String hemocenterName;
    private LocalTime scheduledTime;
    private LocalDate scheduledDate;

    public ScheduleResponse() {
    }

    public ScheduleResponse(int hemocenterUuid, int scheduleHemocenterUuid, String hemocenterName, LocalTime scheduledTime, LocalDate scheduledDate) {
        this.hemocenterUuid = hemocenterUuid;
        this.scheduleHemocenterUuid = scheduleHemocenterUuid;
        this.hemocenterName = hemocenterName;
        this.scheduledTime = scheduledTime;
        this.scheduledDate = scheduledDate;
    }

    public int getHemocenterUuid() {
        return hemocenterUuid;
    }

    public void setHemocenterUuid(int hemocenterUuid) {
        this.hemocenterUuid = hemocenterUuid;
    }

    public int getScheduleHemocenterUuid() {
        return scheduleHemocenterUuid;
    }

    public void setScheduleHemocenterUuid(int scheduleHemocenterUuid) {
        this.scheduleHemocenterUuid = scheduleHemocenterUuid;
    }

    public String getHemocenterName() {
        return hemocenterName;
    }

    public void setHemocenterName(String hemocenterName) {
        this.hemocenterName = hemocenterName;
    }

    public LocalTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }
}
