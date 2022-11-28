package com.example.start.hemomanager.v2.controller;

import com.example.start.hemomanager.v2.domain.Donor;
import com.example.start.hemomanager.v2.domain.Hemocenter;
import com.example.start.hemomanager.v2.dto.DonorSignInDTO;
import com.example.start.hemomanager.v2.dto.HemocenterSignInDTO;
import com.example.start.hemomanager.v2.dto.LoginDTO;
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

    @Test
    @DisplayName("Testar status 422 da função de 'singIn' do HemocenterController")
    public void testeSignIn(){

        HemocenterSignInDTO hemocenterSignInDTO =  new HemocenterSignInDTO();


        hemocenterSignInDTO.setEmail("juliacarolina@gmail.com");
        hemocenterSignInDTO.setCnpj("44318783863");

        Mockito.when(hemocenterRepository.existsByEmailAndCnpj(hemocenterSignInDTO.getEmail(),
                hemocenterSignInDTO.getCnpj())).thenReturn(true);

        ResponseEntity responseEntity = hemocenterController.signIn(hemocenterSignInDTO);

        assertEquals(422,responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity.getBody());

    }

    @Test
    @DisplayName("Testar status 200 da função de 'singIn' do HemocenterController")
    public void segundoTesteSignIn(){

        HemocenterSignInDTO hemocenterSignInDTO =  new HemocenterSignInDTO();

        hemocenterSignInDTO.setEmail("carolina@gmail.com");
        hemocenterSignInDTO.setCnpj("44318783863");

        Mockito.when(hemocenterRepository.existsByEmailAndCnpj(hemocenterSignInDTO.getEmail(),
                hemocenterSignInDTO.getCnpj())).thenReturn(false);

        Hemocenter hemocenter = new Hemocenter();
        hemocenter.setEmail("carolina@gmail.com");
        hemocenter.setCnpj("44318783863");
        Mockito.when(hemocenterRepository.save(hemocenter)).thenReturn(new Hemocenter());

        ResponseEntity responseEntity = hemocenterController.signIn(hemocenterSignInDTO);

        assertEquals(201,responseEntity.getStatusCodeValue());
        assertNull(responseEntity.getBody());

    }

    @Test
    @DisplayName("Testar status 404 da função 'logar' do HemocenterController")
    public void testeLogin() {

        LoginDTO hemocenterDTO =  new  LoginDTO();
        hemocenterDTO .setEmail("carolina@gmail.com");
        hemocenterDTO .setPassword("jahjah123@");

        Mockito.when(hemocenterRepository.findByEmailAndPassword(
                hemocenterDTO.getEmail(),
                hemocenterDTO.getPassword())).thenReturn(null);

        ResponseEntity responseEntity = hemocenterController.loginWithReturn(hemocenterDTO);

        assertEquals(404, responseEntity.getStatusCodeValue());
        assertNull(responseEntity.getBody());
    }

//    @Test
//    @DisplayName("Testar status 200 da função 'logar' do HemocenterController")
//    public void segundoTesteLogin(){
//
//        LoginDTO hemocenterDTO = new LoginDTO();
//
//        hemocenterDTO.setEmail("juliacarolina@gmail.com");
//        hemocenterDTO.setPassword("juhjuh123@");
//        Mockito.when(hemocenterRepository.findByEmailAndPassword(
//                hemocenterDTO.getEmail(),
//                hemocenterDTO.getPassword())).thenReturn(new HemocenterSignInDTO());
//
//        ResponseEntity responseEntity = hemocenterController.loginWithReturn(hemocenterDTO);
//
//        assertEquals(200,responseEntity.getStatusCodeValue());
//        assertNull(responseEntity.getBody());
//
//    }
//
//    @Test
//    @DisplayName("Testar GET do getAllHemocenters do HemocenterController")
//    public void testeGetAllHemocenters(){
//
//
//        Hemocenter hemocenter = new Hemocenter();
//
//        Mockito.when(hemocenterRepository.save(hemocenter)).thenReturn(hemocenter);
//
//        assertInstanceOf(Hemocenter.class, hemocenterController.insertHemocenter(hemocenter));
//
//    }
//
//
//    @Test
//    @DisplayName("Testar o POST do .save Hemocenter")
//    public void testePostSave(){
//
//            Hemocenter hemocenter = new Hemocenter();
//
//        Mockito.when(hemocenterRepository.save(hemocenter)).thenReturn(hemocenter);
//
//        assertInstanceOf(Hemocenter.class, hemocenterController.insertHemocenter(hemocenter));
//    }


}