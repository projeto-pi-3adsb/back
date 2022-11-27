package com.example.start.hemomanager.v2.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "stock")
public class Stock {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate insertDate = LocalDate.now(); // data de inserção no estoque
    @NotBlank private String bloodType;
    @NotNull @PastOrPresent private LocalDate collectionDate; // data da coleta
    @OneToOne(fetch = FetchType.LAZY) private Hemocenter hemocenter;

    public Stock() {
    }

    public Stock(String bloodType) {
        this.bloodType = bloodType;
    }

    public Stock(String bloodType, LocalDate collectionDate) {
        this.bloodType = bloodType;
        this.collectionDate = collectionDate;
    }

    public Stock(String bloodType, LocalDate collectionDate, Hemocenter hemocenter) {
        this.bloodType = bloodType;
        this.collectionDate = collectionDate;
        this.hemocenter = hemocenter;
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

    public Hemocenter getHemocenter() {
        return hemocenter;
    }

    public void setHemocenter(Hemocenter hemocenter) {
        this.hemocenter = hemocenter;
    }
}
