package com.example.start.hemomanager.v2.controller;

import com.example.start.hemomanager.v2.domain.Donor;
import com.example.start.hemomanager.v2.domain.Hemocenter;
import com.example.start.hemomanager.v2.dto.DonorSignInDTO;
import com.example.start.hemomanager.v2.dto.HemocenterSignInDTO;
import com.example.start.hemomanager.v2.repository.DonorRepository;
import com.example.start.hemomanager.v2.repository.HemocenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class HemocenterControllerTest {

    @Autowired
    private HemocenterController hemocenterController;

    @MockBean
    private HemocenterRepository hemocenterRepository;

//    @Test
//    @DisplayName("Testar status da função de logar do HemocenterController")
//    public void testeSingIn(){
//
//        Mockito.when(hemocenterRepository.existsByEmailAndCnpj("juliacarolina@gmail.com","44318783863")).thenReturn(new Hemocenter().);
//
//        ResponseEntity<Donor> responseEntity = hemocenterController.signIn((HemocenterSignInDTO) new Hemocenter());
//
//        assertEquals(200,responseEntity.getStatusCodeValue());
//        assertNotNull(responseEntity.getBody());
//    }

}