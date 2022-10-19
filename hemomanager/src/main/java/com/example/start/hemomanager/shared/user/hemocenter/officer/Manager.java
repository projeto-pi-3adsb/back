package com.example.start.hemomanager.shared.user.hemocenter.officer;
import com.example.start.hemomanager.shared.enumerators.BloodType;
import com.example.start.hemomanager.shared.stock.Stock;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
public class Manager extends Officer implements Stock {

    public Manager(UUID uuid, String name, String cpfCnpj, String email, String password, boolean logged, boolean admin) {
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
