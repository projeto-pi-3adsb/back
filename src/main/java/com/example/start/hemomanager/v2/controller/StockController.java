package com.example.start.hemomanager.v2.controller;

import com.example.start.hemomanager.v2.domain.Hemocenter;
import com.example.start.hemomanager.v2.domain.Stock;
import com.example.start.hemomanager.v2.dto.SimpleStockDTO;
import com.example.start.hemomanager.v2.dto.StockDTO;
import com.example.start.hemomanager.v2.repository.HemocenterRepository;
import com.example.start.hemomanager.v2.repository.StockRepository;
import com.example.start.hemomanager.v2.response.StockSimpleResponse;
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
    @Autowired private HemocenterRepository hemocenterRepository;
    List<Stock> bags = new ArrayList<>();

    @PostMapping
    public ResponseEntity insertBag(@RequestBody StockDTO stockDTO) {
        String bloodType = stockDTO.getBloodType();

        if (!hemocenterRepository.existsById(stockDTO.getHemocenter())) {
            return ResponseEntity.status(404).body("Hemocentro não encontrado.");
        }
        if (!stockDTO.validateBloodType(bloodType)) {
            return ResponseEntity.status(422).body("Erro na inserção da bolsa. Verifique o tipo de sangue da bolsa.");
        }

        Stock stock = new Stock();

        Hemocenter hemocenterToSave = new Hemocenter();
        hemocenterToSave.setUuid(stockDTO.getHemocenter());

        stock.setHemocenter(hemocenterToSave);
        stock.setBloodType(bloodType);
        stock.setCollectionDate(stockDTO.getCollectionDate());
        Stock saved = stockRepository.save(stock);

        BeanUtils.copyProperties(stockDTO, stock);
        return ResponseEntity.status(200).body(saved);
    }

    @GetMapping
    public List<Stock> getAllBags() {
        return stockRepository.findAll();
    }

    @GetMapping("/type/{bloodType}")
    public long getTypeAPosBags(@PathVariable String bloodType) {
        return stockRepository.countByType(bloodType);
    }

    @GetMapping("/bloodType/{hemocenter}")
    public List<StockSimpleResponse> groupBy(@PathVariable Integer hemocenter) {
        return stockRepository.groupByBloodType(hemocenter);
    }
}
