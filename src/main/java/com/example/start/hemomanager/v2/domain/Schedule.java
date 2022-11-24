package com.example.start.hemomanager.v2.domain;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uuid;
    @OneToOne
    private Donor scheduledDonor;
    @OneToOne
    private Hemocenter hemocenter;
    @ManyToOne
    private ScheduleHemocenter scheduleHemocenter;


    public Schedule() {}

    public Schedule(Donor scheduledDonor, Hemocenter hemocenter, ScheduleHemocenter scheduleHemocenter) {
        this.scheduledDonor = scheduledDonor;
        this.hemocenter = hemocenter;
        this.scheduleHemocenter = scheduleHemocenter;
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public Donor getScheduledDonor() {
        return scheduledDonor;
    }

    public void setScheduledDonor(Donor scheduledDonor) {
        this.scheduledDonor = scheduledDonor;
    }

    public Hemocenter getHemocenter() {
        return hemocenter;
    }

    public void setHemocenter(Hemocenter hemocenter) {
        this.hemocenter = hemocenter;
    }

    public ScheduleHemocenter getScheduleHemocenter() {
        return scheduleHemocenter;
    }

    public void setScheduleHemocenter(ScheduleHemocenter scheduleHemocenter) {
        this.scheduleHemocenter = scheduleHemocenter;
    }
}
