package com.example.start.hemomanager.v2.controller;

import com.example.start.hemomanager.v2.domain.Donor;
import com.example.start.hemomanager.v2.domain.Hemocenter;
import com.example.start.hemomanager.v2.domain.Stock;
import com.example.start.hemomanager.v2.domain.dto.InputValidation;
import com.example.start.hemomanager.v2.domain.dto.StockDTO;
import com.example.start.hemomanager.v2.repository.HemocenterRepository;
import com.example.start.hemomanager.v2.repository.StockRepository;
import com.example.start.hemomanager.v2.response.StockFullResponse;
import com.example.start.hemomanager.v2.response.StockSimpleResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

@RestController @RequestMapping("/stock")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StockController {
    @Autowired private StockRepository stockRepository;
    @Autowired private HemocenterRepository hemocenterRepository;
    private InputValidation validation;
    List<Stock> bags = new ArrayList<>();

    @PostMapping("/{hemocenter}")
    public ResponseEntity<Stock> insertBag(@PathVariable Integer hemocenter, @RequestBody StockDTO stockDTO) {
        String bloodType = stockDTO.getBloodType();
        LocalDate collectionDate = stockDTO.getCollectionDate();

        if (!hemocenterRepository.existsById(hemocenter)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hemocentro não encontrado.");

        Stock stock = new Stock();
        Hemocenter hemocenterToSave = new Hemocenter();

        hemocenterToSave.setUuid(hemocenter);
        stock.setHemocenter(hemocenterToSave);
        stock.setBloodType(bloodType);
        stock.setCollectionDate(collectionDate);

        Stock saved = stockRepository.save(stock);
        BeanUtils.copyProperties(stockDTO, stock);

        return ResponseEntity.status(200).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Stock>> getAllBags() {
        List<Stock> stockList = stockRepository.findAll();
        if (stockList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Erro na busca do estoque.");
        }
        return ResponseEntity.status(200).body(stockList);
    }

    @GetMapping("/type/{bloodType}")
    public ResponseEntity<Long> getTypeAPosBags(@PathVariable String bloodType) {
        long counter = stockRepository.countByType(bloodType);
        if (counter == 0) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhuma bolsa foi encontrada.");

        return ResponseEntity.status(200).body(counter);
    }

    @GetMapping("/bloodType/{hemocenter}")
    public ResponseEntity<List<StockSimpleResponse>> groupBy(@PathVariable Integer hemocenter) {
        return (hemocenterRepository.existsById(hemocenter)) ? ResponseEntity.status(200).body(stockRepository.groupByBloodType(hemocenter)) : ResponseEntity.status(404).build();
    }

    @GetMapping("/full/{hemocenter}")
    public ResponseEntity<List<StockFullResponse>> getAllFromStockHemocenter(@PathVariable Integer hemocenter) {
        return (hemocenterRepository.existsById(hemocenter)) ? ResponseEntity.status(200).body(stockRepository.findByHemocenter(hemocenter)) : ResponseEntity.status(404).build();
    }

    @GetMapping("/download-csv/{hemocenter}")
    public ResponseEntity<Resource> downloadCSV(@PathVariable Integer hemocenter) throws FileNotFoundException {
        List<StockFullResponse> stockList = stockRepository.findByHemocenter(hemocenter);
        String nomeArq = "stock.csv";

        try {
            FileWriter arq = new FileWriter(nomeArq);
            Formatter saida = this.formaterCsvByRe(stockList, new Formatter(arq));
            saida.close();
            arq.close();
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o aquivo");
            System.exit(1);
        } catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar o aquivo");
        }

        File initialFile = new File(nomeArq);
        InputStream targetStream = new FileInputStream(initialFile);

        InputStreamResource file = new InputStreamResource(targetStream);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + nomeArq)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    private Formatter formaterCsvByRe(List<StockFullResponse> stockList, Formatter saida) {
        if (stockList.size() > 1) {
            formaterCsvByRe(stockList.subList(0, stockList.size()-1), saida);
        }

        StockFullResponse stock = stockList.get(stockList.size()-1);
        saida.format("%s;%s;%s;%s;\n",
            stock.getBloodType(),
            stock.getCollectionDate(),
            stock.getInsertDate()
        );

        return saida;
    }
    @DeleteMapping("/{hemocenterId}/{bagId}")
    public ResponseEntity<String> deleteBag(@PathVariable int hemocenterId, @PathVariable Integer bagId) {
        if (!hemocenterRepository.existsById(hemocenterId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hemocentro não encontrado.");
        if (!stockRepository.existsById(bagId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bolsa não encontrada.");

        stockRepository.deleteById(bagId);
        return ResponseEntity.status(200).body("Sucesso na exclusão da bolsa.");
    }
}
