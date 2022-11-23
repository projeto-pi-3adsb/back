package com.example.start.hemomanager.v2.domain;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class AvailableTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uuid;
    private LocalTime time;
    @ManyToOne
    private Hemocenter hemocenter;

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Hemocenter getHemocenter() {
        return hemocenter;
    }

    public void setHemocenter(Hemocenter hemocenter) {
        this.hemocenter = hemocenter;
    }
}
