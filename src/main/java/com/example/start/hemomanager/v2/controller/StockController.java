package com.example.start.hemomanager.v2.controller;

import com.example.start.hemomanager.v2.domain.Stock;
import com.example.start.hemomanager.v2.domain.dto.StockDTO;
import com.example.start.hemomanager.v2.response.StockFullResponse;
import com.example.start.hemomanager.v2.response.StockSimpleResponse;
import com.example.start.hemomanager.v2.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.List;

@RestController @RequestMapping("/stock")
public class StockController {

    @Autowired
    StockService stockService;

    @PostMapping("/{hemocenter}")
    public ResponseEntity<Stock> insertBag(@PathVariable Integer hemocenter, @RequestBody StockDTO stockDTO) {
        return stockService.insertBag(hemocenter, stockDTO);
    }

    @GetMapping
    public ResponseEntity<List<Stock>> getAllBags() {
        return stockService.getAllBags();

    }

    @GetMapping("/type/{bloodType}")
    public ResponseEntity<Long> getTypeAPosBags(@PathVariable String bloodType) {
        return stockService.getTypeAPosBags(bloodType);

    }

    @GetMapping("/bloodType/{hemocenter}")
    public ResponseEntity<List<StockSimpleResponse>> groupBy(@PathVariable Integer hemocenter) {
        return stockService.groupBy(hemocenter);

    }

    @GetMapping("/full/{hemocenter}")
    public ResponseEntity<List<StockFullResponse>> getAllFromStockHemocenter(@PathVariable Integer hemocenter) {
        return stockService.getAllFromStockHemocenter(hemocenter);

    }

    @GetMapping("/download-csv/{hemocenter}")
    public ResponseEntity<Resource> downloadCSV(@PathVariable Integer hemocenter) throws FileNotFoundException {
        return stockService.downloadCSV(hemocenter);

    }

    private Formatter formaterCsvByRe(List<StockFullResponse> stockList, Formatter saida) {
        return stockService.formaterCsvByRe(stockList, saida);

    }

    @DeleteMapping("/{hemocenterId}/{bagId}")
    public ResponseEntity<String> deleteBag(@PathVariable int hemocenterId, @PathVariable Integer bagId) {
        return stockService.deleteBag(hemocenterId, bagId);
    }
}
