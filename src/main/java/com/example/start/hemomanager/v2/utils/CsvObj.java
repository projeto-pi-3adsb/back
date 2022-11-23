package com.example.start.hemomanager.v2.utils;

import com.example.start.hemomanager.v2.domain.Donor;
import com.example.start.hemomanager.v2.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/csv")
public class CsvObj {
    @Autowired private DonorRepository donorRepository;
    ListaObj<Donor> donors = new ListaObj<>(10);

    @GetMapping
    public ResponseEntity<Resource> donorsDowloads() throws FileNotFoundException {
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;
        String nomeArq = "Donors.csv";
        ListaObj<Donor> donors = new ListaObj<>(10);
        Donor donorTest = new Donor(
                "Cindy",
                "cindy@gmail.com",
                "123456789",
                "23774360804",
                LocalDate.now(),
                "F",
                "12991340567",
                true
        );
        donors.add(donorTest);

        try{
            arq = new FileWriter(nomeArq);
            saida = new Formatter(arq);
        } catch (IOException erro){
            System.out.println("Erro ao abrir o aquivo");
            System.exit(1);
        }

        try{
            for (int i = 0; i < donors.getTamanho();i++){
                Donor donor = donors.getElemento(i);
                saida.format("%s; %s; %s\n",
                        donor.getName(),
                        donor.getCpf(),
                        donor.getEmail()
                );
            }
        } catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar o aquivo");
            deuRuim = true;
        }
        finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException erro) {
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
