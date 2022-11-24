package com.example.start.hemomanager.v2.controller;
import com.example.start.hemomanager.v2.domain.Donor;
import com.example.start.hemomanager.v2.dto.DonorSignInDTO;
import com.example.start.hemomanager.v2.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

@SpringBootTest
class DonorControllerTest {

    @Autowired
    private DonorController donorController;

    @MockBean
    private DonorRepository donorRepository;

    @Test
    @DisplayName("Testar status da função de 'singIn' do DonorController")
    public void testeSignIn(){

        DonorSignInDTO donorSignDTO =  new DonorSignInDTO();
        donorSignDTO.setEmail("juliacarolina@gmail.com");
        donorSignDTO.setCpf("44318783863");
        Mockito.when(donorRepository.existsByEmailAndCpf(donorSignDTO.getEmail(), donorSignDTO.getCpf())).thenReturn(true);

        ResponseEntity responseEntity = donorController.signIn(donorSignDTO);

        assertEquals(422,responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity.getBody());

    }

//    @Test
//    @DisplayName("Testar status da função de logar do DonorController")
//    public void testeLogin(){
//
//        Mockito.when(donorRepository.findByEmailAndPassword("juliacarolina@gmail.com","Ju1juh@").thenReturn();
//
//        ResponseEntity<Donor> responseEntity = donorController.signIn((DonorSignInDTO) new Donor());
//
//        assertEquals(200,responseEntity.getStatusCodeValue());
//        assertNotNull(responseEntity.getBody());
//
//    }





}