package com.example.start.hemomanager.v2.controller;

import com.example.start.hemomanager.v2.domain.Donor;
import com.example.start.hemomanager.v2.domain.dto.StockDTO;
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
import java.nio.charset.StandardCharsets;
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
            saida.format("%s;%s;%s;%s;%s;%s\n",
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

    @GetMapping(value = "/arquivo-txt", produces = "text/plain")
    public ResponseEntity<byte[]> buscaArquivoTxt() {
        String idProprietario = "Alex Barreira;alex.kbassssdhfhashdf@gmail.com;67901355026;2000-05-10;M;11912345678";

        byte[] arquivoTxt = idProprietario.getBytes(StandardCharsets.UTF_8);

        return ResponseEntity
                .status(200)
                .header("content-disposition", "attachment; filename=\"item.txt\"")
                .body(arquivoTxt);
    }

    @PostMapping(value = "/arquivo-txt")
    public ResponseEntity salvaTxt(
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        StockController controller = new StockController();
        String itemString = new String(file.getBytes(), "UTF-8");
        System.out.println(itemString);
        Integer id = Integer.valueOf(itemString.substring(0, 1));
        String bloodType = itemString.substring(3, 5);
        LocalDate descricao = LocalDate.parse(itemString.substring(6, 16));

        StockDTO dto = new StockDTO(bloodType, descricao);
        controller.insertBag(id, dto);
        return ResponseEntity.status(201).build();

    }



    @PostMapping(value = "/upload-txt", consumes = "text/*")
    public String uploadTxt(@RequestBody byte[] fileTxt, MultipartFile file) throws IOException {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(Objects.requireNonNull(file.getOriginalFilename())));

            FileReader arq = new FileReader(file.getOriginalFilename());
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

    @GetMapping("/dowload-modelo-csv")
    public ResponseEntity<Resource> dowloadsModeloCsv() throws FileNotFoundException {
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;
        String nomeArq = "Donors.csv";

        try {
            arq = new FileWriter(nomeArq);
            saida = new Formatter(arq);
            saida.format("NOME;EMAIL;CPF;DATA DE NASCIMENTO;GÊNERO;TELEFONE\n");

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

    @GetMapping("/dowload-modelo-txt")
    public ResponseEntity<Resource> dowloadsModeloTxt() throws FileNotFoundException {
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;
        String nomeArq = "Donors.txt";

        try {
            arq = new FileWriter(nomeArq);
            saida = new Formatter(arq);
            saida.format("Nome completo\n" +
                            "Email\n" +
                            "CPF\n" +
                            "Data de nascimento\n" +
                            "Gênero\n" +
                            "Telefone\n"
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
}
