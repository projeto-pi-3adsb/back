package com.example.start.hemomanager.shared.stock;
import com.example.start.hemomanager.shared.enumerators.BloodType;

public interface Stock {
    public void insertBag();

    public void removeBag();

    public void checkStock();

    public void checkStockByType(BloodType bloodType);
}
