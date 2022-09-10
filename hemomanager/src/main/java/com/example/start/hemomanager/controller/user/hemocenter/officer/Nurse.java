package com.example.start.hemomanager.controllers.user.hemocenter.officer;
import com.example.start.hemomanager.controllers.enumerators.BloodType;
import com.example.start.hemomanager.controllers.stock.Stock;

import java.util.UUID;

public class Nurse extends Officer implements Stock {

    public Nurse(UUID uuid, String name, String cpfCnpj, String email, String password, boolean logged, boolean admin) {
        super(uuid, name, cpfCnpj, email, password, logged, admin);
    }

    @Override
    public boolean doLogin() {
        return false;
    }

    @Override
    public boolean doLogout() {
        return false;
    }

    @Override
    public void insertBag() {
        System.out.println("bolsa inserida");
    }

    @Override
    public void removeBag() {
        System.out.println("bolsa removida");
    }

    @Override
    public void checkStock() {
        System.out.println("estoque checado");
    }

    @Override
    public void checkStockByType(BloodType bloodType) {
        System.out.println("estoque checado");
    }
}
