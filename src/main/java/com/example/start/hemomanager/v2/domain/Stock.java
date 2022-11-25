package com.example.start.hemomanager.v2.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Stock {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate insertDate; // data de inserção no estoque
    private String bloodType;
    private LocalDate collectionDate; // data da coleta

    public Stock() {
    }

    public Stock(LocalDate insertDate, String bloodType, LocalDate collectionDate) {
        this.insertDate = insertDate;
        this.bloodType = bloodType;
        this.collectionDate = collectionDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(LocalDate insertDate) {
        this.insertDate = insertDate;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public LocalDate getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(LocalDate collectionDate) {
        this.collectionDate = collectionDate;
    }
}
