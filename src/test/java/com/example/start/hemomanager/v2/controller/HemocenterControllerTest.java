package com.example.start.hemomanager.v2.controller;

import com.example.start.hemomanager.v2.domain.Hemocenter;
import com.example.start.hemomanager.v2.domain.dto.HemocenterSignInDTO;
import com.example.start.hemomanager.v2.domain.dto.LoginDTO;
import com.example.start.hemomanager.v2.repository.HemocenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class HemocenterControllerTest {

    @Autowired
    private HemocenterController hemocenterController;

    @MockBean
    private HemocenterRepository hemocenterRepository;

//    @Test
//    @DisplayName("Testar status 422 da função de 'singIn' do HemocenterController")
//    public void testeSignIn(){
//
//        HemocenterSignInDTO hemocenterSignInDTO =  new HemocenterSignInDTO();
//
//
//        hemocenterSignInDTO.setEmail("juliacarolina@gmail.com");
//        hemocenterSignInDTO.setCnpj("44318783863");
//
//        Mockito.when(hemocenterRepository.existsByEmailAndCnpj(hemocenterSignInDTO.getEmail(),
//                hemocenterSignInDTO.getCnpj())).thenReturn(true);
//
//        ResponseEntity responseEntity = hemocenterController.loginHemocenter(hemocenterSignInDTO);
//
//        assertEquals(422,responseEntity.getStatusCodeValue());
//        assertNotNull(responseEntity.getBody());
//
//    }

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

        ResponseEntity responseEntity = hemocenterController.createHemocenter(hemocenterSignInDTO);

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

        ResponseEntity responseEntity = hemocenterController.loginHemocenter(hemocenterDTO);

        assertEquals(404, responseEntity.getStatusCodeValue());
        assertNull(responseEntity.getBody());
    }

    @Test
    @DisplayName("Testar status 200 da função 'logar' do HemocenterController")
    public void segundoTesteLogin(){

        LoginDTO hemocenterDTO = new LoginDTO();

        hemocenterDTO.setEmail("juliacarolina@gmail.com");
        hemocenterDTO.setPassword("juhjuh123@");

        Hemocenter hemocenter = new Hemocenter();
        hemocenter.setEmail("juliacarolina@gmail.com");
        hemocenter.setPassword("juhjuh123@");
        Mockito.when(hemocenterRepository.findByEmailAndPassword(
                hemocenterDTO.getEmail(),
                hemocenterDTO.getPassword())).thenReturn(hemocenter);

        ResponseEntity responseEntity = hemocenterController.loginHemocenter(hemocenterDTO);

        assertEquals(200,responseEntity.getStatusCodeValue());


    }

    @Test
    @DisplayName("Testar GET do getAllHemocenters do HemocenterController")
    public void testeGetAllHemocenters(){

        Hemocenter hemocenter1 = new Hemocenter();
        hemocenter1.setCnpj("123");
        Hemocenter hemocenter2 = new Hemocenter();
        List<Hemocenter> lista = new ArrayList<>();
        lista.add(hemocenter1);
        lista.add(hemocenter2);


        Mockito.when(hemocenterRepository.findAll()).thenReturn(lista);

        List<Hemocenter> listaTeste = hemocenterController.getAllHemocenters().getBody();

        assertEquals(listaTeste.size(),2);
        assertEquals(listaTeste.get(0).getCnpj(),"123");

    }


    @Test
    @DisplayName("Testar o POST do .save Hemocenter")
    public void testePostSave(){

        HemocenterSignInDTO dto = new HemocenterSignInDTO();
        dto.setEmail("juliacarolina@gmail.com");
        dto.setPassword("juhjuh123@");

        Hemocenter hemocenter = new Hemocenter();
        hemocenter.setEmail("juliacarolina@gmail.com");
        hemocenter.setPassword("juhjuh123@");
        Mockito.when(hemocenterRepository.existsByEmailAndCnpj(dto.getEmail(),dto.getPassword()))
                        .thenReturn(false);

        Mockito.when(hemocenterRepository.save(hemocenter)).thenReturn(hemocenter);
        ResponseEntity hemocenterSalvo = hemocenterController.createHemocenter(dto);

        assertEquals(hemocenterSalvo.getStatusCodeValue(),201);

    }


}