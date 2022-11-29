package com.example.start.hemomanager.v2.controller;

import com.example.start.hemomanager.v2.domain.Donor;
import com.example.start.hemomanager.v2.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/platelets")
public class PlateletController {

    @Autowired
    private DonorRepository donorRepository;

    private final Path root = Paths.get("uploads");

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

    @GetMapping("/dowload-txt")
    public ResponseEntity<Resource> dowloadsTxt(Integer donorId) throws FileNotFoundException {
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;
        String nomeArq = "Donors.txt";

        try {
            arq = new FileWriter(nomeArq);
            saida = new Formatter(arq);
            Optional<Donor> donorOptional = donorRepository.findById(donorId);
            Donor donor = donorOptional.get();
            saida.format("Nome completo: %s\n" +
                            "CPF: %s\n" +
                            "Email: %s\n" +
                            "Data de nascimento: %s\n" +
                            "Telefone: %s\n" +
                            "Gênero: %s\n",
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
                .contentType(MediaType.parseMediaType("application/txt"))
                .body(file);
    }

    @PostMapping("/upload-csv")
    public String uploadCsv(@RequestParam("file") MultipartFile file) throws IOException {
        FileReader arq = null;
        Scanner entrada = null;

        try {
            Files.copy(file.getInputStream(), this.root.resolve(Objects.requireNonNull(file.getOriginalFilename())));

            arq = new FileReader(file.getOriginalFilename());
            entrada = new Scanner(arq).useDelimiter(";|\\n");

            while(entrada.hasNext()){
                Donor donor = new Donor(
                    entrada.next(),
                    entrada.next(),
                    "123",
                    entrada.next(),
                    LocalDate.parse(entrada.next()),
                    entrada.next(),
                    entrada.next(),
                    true
                );

                donorRepository.save(donor);
            }

            Files.delete(this.root.resolve(Objects.requireNonNull(file.getOriginalFilename())));
        } catch (Exception e) {
            Files.delete(this.root.resolve(Objects.requireNonNull(file.getOriginalFilename())));

            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }

            return e.getMessage();
        }
        return "Sucesso";
    }

    @PostMapping("/upload-txt")
    public String uploadTxt(@RequestParam("file") MultipartFile file) throws IOException {
        FileReader arq = null;
        Scanner entrada = null;

        try {
            Files.copy(file.getInputStream(), this.root.resolve(Objects.requireNonNull(file.getOriginalFilename())));

            arq = new FileReader(file.getOriginalFilename());
            BufferedReader lerArq = new BufferedReader(arq);

            Donor donor = new Donor(
                    lerArq.readLine(),
                    lerArq.readLine(),
                    "123",
                    lerArq.readLine(),
                    LocalDate.parse(lerArq.readLine()),
                    lerArq.readLine(),
                    lerArq.readLine(),
                    true
            );
            donorRepository.save(donor);

            Files.delete(this.root.resolve(Objects.requireNonNull(file.getOriginalFilename())));
        } catch (Exception e) {
            Files.delete(this.root.resolve(Objects.requireNonNull(file.getOriginalFilename())));

            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }

            return e.getMessage();
        }
        return "Sucesso";
    }
}
