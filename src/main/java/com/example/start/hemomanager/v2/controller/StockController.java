package com.example.start.hemomanager.v2.controller;

import com.example.start.hemomanager.v2.domain.Stock;
import com.example.start.hemomanager.v2.dto.StockDTO;
import com.example.start.hemomanager.v2.repository.StockRepository;
import org.springframework.beans.BeanUtils;
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
        BeanUtils.copyProperties(stockDTO, stock);
        Stock saved = stockRepository.save(stock);
        return ResponseEntity.status(200).body(saved);
    }

    @GetMapping
    public long getAllBags() {
        return stockRepository.countByBloodType();
    }

    @GetMapping("/APos")
    public long getTypeAPosBags() {
        return stockRepository.countByTypeAPos();
    }

    @GetMapping("/ANeg")
    public long getTypeANegBags() {
        return stockRepository.countByTypeANeg();
    }

    @GetMapping("/BPos")
    public long getTypeBPosBags() {
        return stockRepository.countByTypeBPos();
    }

    @GetMapping("/BNeg")
    public long getTypeBNegBags() {
        return stockRepository.countByTypeBNeg();
    }

    @GetMapping("/ABPos")
    public long getTypeABPosBags() {
        return stockRepository.countByTypeABPos();
    }

    @GetMapping("/ABNeg")
    public long getTypeABNegBags() {
        return stockRepository.countByTypeABNeg();
    }

    @GetMapping("/OPos")
    public long getTypeOPosBags() {
        return stockRepository.countByTypeOPos();
    }

    @GetMapping("/ONeg")
    public long getTypeONegBags() {
        return stockRepository.countByTypeONeg();
    }
}
