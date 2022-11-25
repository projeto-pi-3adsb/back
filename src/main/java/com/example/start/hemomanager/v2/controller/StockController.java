package com.example.start.hemomanager.v2.controller;

import com.example.start.hemomanager.v2.domain.Stock;
import com.example.start.hemomanager.v2.dto.SimpleStockDTO;
import com.example.start.hemomanager.v2.dto.StockDTO;
import com.example.start.hemomanager.v2.repository.StockRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController @RequestMapping("/stock")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StockController {
    @Autowired private StockRepository stockRepository;
    List<Stock> bags = new ArrayList<>();

    @PostMapping
    public ResponseEntity insertBag(@RequestBody StockDTO stockDTO) {
        String bloodType = stockDTO.getBloodType();

        if (!stockDTO.validateBloodType(bloodType)) {
            return ResponseEntity.status(422).body("Erro na inserção da bolsa. Verifique o tipo de sangue da bolsa.");
        }

        Stock stock = new Stock();
        BeanUtils.copyProperties(stockDTO, stock);
        Stock saved = stockRepository.save(stock);
        return ResponseEntity.status(200).body(saved);
    }

    @GetMapping
    public List<Stock> getAllBags() {
        return stockRepository.findAll();
    }

    @GetMapping("/type/{bloodType}")
    public long getTypeAPosBags(@RequestParam String bloodType) {
        return stockRepository.countByType(bloodType);
    }
}
