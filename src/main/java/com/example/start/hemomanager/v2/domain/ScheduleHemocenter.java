package com.example.start.hemomanager.v2.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class ScheduleHemocenter {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uuid;
    private LocalTime scheduledTime;
    private LocalDate scheduledDate;
    @ManyToOne
    private Hemocenter hemocenter;

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
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

    public Hemocenter getHemocenter() {
        return hemocenter;
    }

    public void setHemocenter(Hemocenter hemocenter) {
        this.hemocenter = hemocenter;
    }
}
