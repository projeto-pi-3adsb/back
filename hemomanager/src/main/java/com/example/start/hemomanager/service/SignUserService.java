package com.example.start.hemomanager.service;

import com.example.start.hemomanager.shared.user.User;
import com.example.start.hemomanager.shared.user.donor.Donor;
import com.example.start.hemomanager.shared.user.hemocenter.officer.Manager;
import com.example.start.hemomanager.shared.user.hemocenter.officer.Nurse;
import com.example.start.hemomanager.shared.user.hemocenter.officer.Officer;
import com.example.start.hemomanager.shared.user.hemocenter.officer.Receptionist;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

public class SignUserService {
    // this List<User> is only for create and manipulate requests on Postman.
    // Next version will remove it.
    List<User> users = new ArrayList<>();
    List<Officer> officers = new ArrayList<>();
    List<Donor> donors = new ArrayList<>();

    public List<User> getAllUsers() {
        return (users.size() != 0) ? users : null ;
    }

    public List<Officer> getAllOfficers() {
        return (officers.size() != 0) ? officers : null ;
    }

    public Officer getAllAuthorizedStockOfficers() {
        for (Officer currentOfficer : officers) {
            if (currentOfficer.isAdmin())
                return currentOfficer;
        }
        return null;
    }

    public Donor insertDonor(Donor donor) {
        donors.add(donor);
        users.add(donor);
        return donor;
    }

    public Manager insertManager(Manager manager) {
        officers.add(manager);
        users.add(manager);
        return manager;
    }

    public Nurse insertNurse(Nurse nurse) {
        officers.add(nurse);
        users.add(nurse);
        return nurse;
    }

    public Receptionist insertReceptionist(Receptionist receptionist) {
        officers.add(receptionist);
        users.add(receptionist);
        return receptionist;
    }

    public User loginDonor(String email, String password) {
        for (User currentUser : users) {
            if (currentUser.authenticate(email, password)) {
                return currentUser;
            }
        }
        return null;
    }

    public Officer loginOfficer(String email, String password) {
        for (Officer currentOfficer : officers) {
            if (currentOfficer.authenticateOfficer(email, password)) {
                return currentOfficer;
            }
        }
        return null;
    }

    public String logoutUser(String email) {
        String currentUserName;
        for (User currentUser : users) {
            if (currentUser.getEmail().equals(email)) {
                currentUserName = currentUser.getName();
                if (currentUser.isLogged()) {
                    currentUser.setLogged(false);
                    return String.format("Logout do usuário %s bem sucedido.", currentUserName);
                }
                return String.format("Não foi possível desconectar o usuário %s. Por favor faça o login antes de tentar desconectar.", currentUserName);
            }
        }
        return String.format("O usuário com o email '%s' não foi encontrado na nossa base de dados.", email);
    }
}
