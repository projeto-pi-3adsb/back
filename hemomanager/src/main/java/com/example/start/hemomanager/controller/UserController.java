package com.example.start.hemomanager.controller;

import com.example.start.hemomanager.listaObj.ListaObj;
import com.example.start.hemomanager.service.LoginUserService;
import com.example.start.hemomanager.service.SignUserService;
import com.example.start.hemomanager.shared.enumerators.BloodType;
import com.example.start.hemomanager.shared.user.User;
import com.example.start.hemomanager.shared.user.donor.Donor;
import com.example.start.hemomanager.shared.user.hemocenter.officer.Manager;
import com.example.start.hemomanager.shared.user.hemocenter.officer.Nurse;
import com.example.start.hemomanager.shared.user.hemocenter.officer.Officer;
import com.example.start.hemomanager.shared.user.hemocenter.officer.Receptionist;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {
    SignUserService signUserService = new SignUserService();
    LoginUserService loginUserService = new LoginUserService();
    // the class LoginUserService will be used after the implementation of DAOs.

    // General registered users management
    @GetMapping("/listUsers")
    public List<User> getAllUsers() {
        return this.signUserService.getAllUsers();
    }

    @DeleteMapping("/logout/{email}")
    public String logoutUser(@PathVariable String email) {
        return this.signUserService.logoutUser(email);
    }

    // Donor management
    @PostMapping("/donor/signup")
    public Donor insertDonor(@RequestBody Donor donor) {
        return this.signUserService.insertDonor(donor);
    }

    @PostMapping("/donor/signin/{email}/{password}")
    public User loginDonor(@PathVariable String email, @PathVariable String password) {
        return this.signUserService.loginDonor(email, password);
    }

    // Officer management
    @PostMapping("/officer/manager/signup")
    public Manager insertManager(@RequestBody Manager manager) {
        return this.signUserService.insertManager(manager);
    }

    @PostMapping("/officer/nurse/signup")
    public Nurse insertNurse(@RequestBody Nurse nurse) {
        return this.signUserService.insertNurse(nurse);
    }

    @PostMapping("/officer/receptionist/signup")
    public Receptionist insertReceptionist(@RequestBody Receptionist receptionist) {
        return this.signUserService.insertReceptionist(receptionist);
    }

    @PostMapping("/officer/signin/{email}/{password}")
    public Officer loginOfficer(@PathVariable String email, @PathVariable String password) {
        return this.signUserService.loginOfficer(email, password);
    }

    @GetMapping("/listOfficers")
    public List<Officer> getAllOfficers() {
        return this.signUserService.getAllOfficers();
    }

    @GetMapping("/listAdminOfficers")
    public Officer getAllAuthorizedStockOfficers() {
        return this.signUserService.getAllAuthorizedStockOfficers();
    }

    @GetMapping("/csv")
    public ResponseEntity<Resource> donorsDowloads() throws FileNotFoundException {
        FileWriter arq = null; // objeto que representa o arquivo de gravação
        Formatter saida = null; // objeto usado para gravar no arquivo
        Boolean deuRuim = false;
        String nomeArq = "Donors.csv"; // acrescenta a extensão .csv ao nome do aquivo
        ListaObj<Donor> donors = new ListaObj<>(10);
        Donor donorTest = new Donor(UUID.randomUUID(),"Lala","23774360804","cindy@gmail.com",
                "123456789",true, new Date(),"F", BloodType.ABNEG,"12991340567"
        ,true,"01313020","175");


        donors.add(donorTest);

        //bloco para abrir o arquivo
        try{
            arq = new FileWriter(nomeArq); // cria ou abre o arquivo
            saida = new Formatter(arq);

        } catch (IOException erro){
            System.out.println("Erro ao abrir o aquivo");
            System.exit(1);
        }

        //bloco para gravar o arquivo
        try{
            // saida.format("ID;NOME;VALOR;ESTOQUE\n");

            for (int i = 0; i < donors.getTamanho();i++){
                Donor donor = donors.getElemento(i);
                saida.format("%s;%s;%s\n",donor.getName(),donor.getCpfCnpj(),
                        donor.getEmail());
            }
        } catch (FormatterClosedException erro){
            System.out.println("Erro ao gravar o aquivo");
            deuRuim = true;
        }
        finally {
            saida.close();

            try{
                arq.close();

            }catch (IOException erro){
                System.out.println("Erro ao fechar o aquivo");
                deuRuim = true;
            }
            if (deuRuim){
                System.exit(1);
            }
        }

        File initialFile = new File(nomeArq);
        InputStream targetStream = new FileInputStream(initialFile);

        InputStreamResource file = new InputStreamResource(targetStream);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + nomeArq)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);

    }
}
