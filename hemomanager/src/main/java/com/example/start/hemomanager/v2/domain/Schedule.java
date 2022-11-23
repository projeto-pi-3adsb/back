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
    private Hemocenter scheduledHemocenter;
    private LocalDate scheduledDate;
    private LocalTime scheduledTime;

    public Schedule(Donor scheduledDonor, Hemocenter scheduledHemocenter, LocalDate scheduledDate, LocalTime scheduledTime) {
        this.scheduledDonor = scheduledDonor;
        this.scheduledHemocenter = scheduledHemocenter;
        this.scheduledDate = scheduledDate;
        this.scheduledTime = scheduledTime;
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

    public Hemocenter getScheduledHemocenter() {
        return scheduledHemocenter;
    }

    public void setScheduledHemocenter(Hemocenter scheduledHemocenter) {
        this.scheduledHemocenter = scheduledHemocenter;
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
