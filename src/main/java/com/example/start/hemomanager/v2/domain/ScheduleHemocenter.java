package com.example.start.hemomanager.v2.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class ScheduleHemocenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uuid;
    @ManyToOne
    private ScheduleHemocenter hemocenter;
    private LocalDate scheduledDate;
    private LocalTime scheduledTime;

    public ScheduleHemocenter(ScheduleHemocenter hemocenter, LocalDate scheduledDate, LocalTime scheduledTime) {
        this.hemocenter = hemocenter;
        this.scheduledDate = scheduledDate;
        this.scheduledTime = scheduledTime;
    }

    public ScheduleHemocenter(){}

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public ScheduleHemocenter gethemocenter() {
        return hemocenter;
    }

    public void sethemocenter(ScheduleHemocenter hemocenter) {
        this.hemocenter = hemocenter;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public LocalTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
}
