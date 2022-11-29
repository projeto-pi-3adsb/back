package com.example.start.hemomanager.v2.controller;

import com.example.start.hemomanager.v2.domain.Donor;
import com.example.start.hemomanager.v2.repository.DonorRepository;
import com.example.start.hemomanager.v2.utils.ListaObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.time.LocalDate;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.Optional;

@RestController
@RequestMapping("/platelets")
public class PlateletController {

    @Autowired
    private DonorRepository donorRepository;

    @GetMapping("/dowload-csv")
    public ResponseEntity<Resource> dowloadsCsv(Integer donorId) throws FileNotFoundException {
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;
        String nomeArq = "Donors.csv";

        try {
            arq = new FileWriter(nomeArq);
            saida = new Formatter(arq);
            Optional<Donor> donorOptional = donorRepository.findById(donorId);
            Donor donor = donorOptional.get();
            saida.format("%s; %s; %s; %s; %s; %s\n",
                    donor.getName(),
                    donor.getCpf(),
                    donor.getEmail(),
                    donor.getBirthDate(),
                    donor.getPhone(),
                    donor.getSex()
            );
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o aquivo");
            System.exit(1);
        } catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar o aquivo");
            deuRuim = true;
        } finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o aquivo");
                deuRuim = true;
            }
            if (deuRuim) {
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
