package com.example.start.hemomanager.controllers.stock;
import com.example.start.hemomanager.controllers.enumerators.BloodType;

public interface Stock {
    public void insertBag();

    public void removeBag();

    public void checkStock();

    public void checkStockByType(BloodType bloodType);
}
