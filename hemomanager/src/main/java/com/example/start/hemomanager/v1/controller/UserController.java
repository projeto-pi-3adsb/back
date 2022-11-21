package com.example.start.hemomanager.v1.controller;

import com.example.start.hemomanager.v1.service.LoginUserService;
import com.example.start.hemomanager.v1.service.SignUserService;
import com.example.start.hemomanager.v1.shared.user.User;
import com.example.start.hemomanager.v1.shared.user.donor.Donor;
import com.example.start.hemomanager.v1.shared.user.hemocenter.officer.Officer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
//    @Autowired private DonorRepository donorRepository;
//    @Autowired private OfficerRepository officerRepository;

//    List<Donor> userList = donorRepository.findAll();
//    List<Officer> officerList = officerRepository.findAll();

    SignUserService signUserService = new SignUserService();
    LoginUserService loginUserService = new LoginUserService();
    // the class LoginUserService will be used after the implementation of DAOs.

    // General registered users management
//    @GetMapping("/listUsers")
//    public ResponseEntity<List<Donor>> getAllUsers() {
//        return userList.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(userList);
//    }

    @DeleteMapping("/logout/{email}")
    public String logoutUser(@PathVariable String email) {
        return this.signUserService.logoutUser(email);
    }

//    // Donor management
//    @PostMapping("/donor/signup")
//    public Donor insertDonor(@RequestBody Donor donor) {
//        return donorRepository.save(donor);
//    }

    @PostMapping("/donor/signin/{email}/{password}")
    public User loginDonor(@PathVariable String email, @PathVariable String password) {
        return this.signUserService.loginDonor(email, password);
    }

//    // Officer management
//    @PostMapping("/officer/manager/signup")
//    public Officer insertManager(@RequestBody Officer manager) {
//        return officerRepository.save(manager);
//    }
//
//    @PostMapping("/officer/nurse/signup")
//    public Officer insertNurse(@RequestBody Officer nurse) {
//        return officerRepository.save(nurse);
//    }
//
//    @PostMapping("/officer/receptionist/signup")
//    public Officer insertReceptionist(@RequestBody Officer receptionist) {
//        return officerRepository.save(receptionist);
//    }

    @PostMapping("/officer/signin/{email}/{password}")
    public Officer loginOfficer(@PathVariable String email, @PathVariable String password) {
        return this.signUserService.loginOfficer(email, password);
    }

//    @GetMapping("/listOfficers")
//    public ResponseEntity<List<Officer>> getAllOfficers() {
//        return officerList.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(officerList);
//    }

    @GetMapping("/listAdminOfficers")
    public Officer getAllAuthorizedStockOfficers() {
        return this.signUserService.getAllAuthorizedStockOfficers();
    }

//    @GetMapping("/csv")
//    public ResponseEntity<Resource> donorsDowloads() throws FileNotFoundException {
//        FileWriter arq = null; // objeto que representa o arquivo de gravação
//        Formatter saida = null; // objeto usado para gravar no arquivo
//        Boolean deuRuim = false;
//        String nomeArq = "Donors.csv"; // acrescenta a extensão .csv ao nome do aquivo
//        ListaObj<Donor> donors = new ListaObj<>(10);
//        Donor donorTest = new Donor(    ,"Lala","23774360804","cindy@gmail.com",
//                "123456789",true, new Date(),"F", BloodType.ABNEG,"12991340567"
//        ,true,"01313020","175");
//
//
//        donors.add(donorTest);
//
//        //bloco para abrir o arquivo
//        try{
//            arq = new FileWriter(nomeArq); // cria ou abre o arquivo
//            saida = new Formatter(arq);
//
//        } catch (IOException erro){
//            System.out.println("Erro ao abrir o aquivo");
//            System.exit(1);
//        }
//
//        //bloco para gravar o arquivo
//        try{
//            // saida.format("ID;NOME;VALOR;ESTOQUE\n");
//
//            for (int i = 0; i < donors.getTamanho();i++){
//                Donor donor = donors.getElemento(i);
//                saida.format("%s;%s;%s\n",donor.getName(),donor.getCpfCnpj(),
//                        donor.getEmail());
//            }
//        } catch (FormatterClosedException erro){
//            System.out.println("Erro ao gravar o aquivo");
//            deuRuim = true;
//        }
//        finally {
//            saida.close();
//
//            try{
//                arq.close();
//
//            }catch (IOException erro){
//                System.out.println("Erro ao fechar o aquivo");
//                deuRuim = true;
//            }
//            if (deuRuim){
//                System.exit(1);
//            }
//        }
//
//        File initialFile = new File(nomeArq);
//        InputStream targetStream = new FileInputStream(initialFile);
//
//        InputStreamResource file = new InputStreamResource(targetStream);
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + nomeArq)
//                .contentType(MediaType.parseMediaType("application/csv"))
//                .body(file);
//
//    }
}
