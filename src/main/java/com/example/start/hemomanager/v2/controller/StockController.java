package com.example.start.hemomanager.v2.controller;

import com.example.start.hemomanager.v2.domain.Stock;
import com.example.start.hemomanager.v2.dto.StockDTO;
import com.example.start.hemomanager.v2.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController @RequestMapping("/stock")
public class StockController {
    @Autowired private StockRepository stockRepository;
    List<Stock> bags = new ArrayList<>();

    @PostMapping
    public ResponseEntity insertBag(@RequestBody StockDTO stockDTO) {
        Stock stock = new Stock();
        Stock saved = stockRepository.save(stock);
        return ResponseEntity.status(200).body(saved);
    }

    @GetMapping
    public Long getAllBags() {
        return stockRepository.countAll();
    }

    @GetMapping("/APos")
    public Long getTypeAPosBags() {
        return stockRepository.countByTypeAPos();
    }

    @GetMapping("/ANeg")
    public Long getTypeANegBags() {
        return stockRepository.countByTypeANeg();
    }

    @GetMapping("/BPos")
    public Long getTypeBPosBags() {
        return stockRepository.countByTypeBPos();
    }

    @GetMapping("/BNeg")
    public Long getTypeBNegBags() {
        return stockRepository.countByTypeBNeg();
    }

    @GetMapping("/ABPos")
    public Long getTypeABPosBags() {
        return stockRepository.countByTypeABPos();
    }

    @GetMapping("/ABNeg")
    public Long getTypeABNegBags() {
        return stockRepository.countByTypeABNeg();
    }

    @GetMapping("/OPos")
    public Long getTypeOPosBags() {
        return stockRepository.countByTypeOPos();
    }

    @GetMapping("/ONeg")
    public Long getTypeONegBags() {
        return stockRepository.countByTypeONeg();
    }
}
