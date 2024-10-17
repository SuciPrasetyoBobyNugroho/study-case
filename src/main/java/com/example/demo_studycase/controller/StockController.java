package com.example.demo_studycase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo_studycase.dto.StockDTO;
import com.example.demo_studycase.model.Stock;
import com.example.demo_studycase.service.StockService;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping("/create")
    public ResponseEntity<Stock> createStock(@ModelAttribute StockDTO stockDTO) {
        try {
            Stock stock = stockService.createStock(stockDTO);
            return ResponseEntity.ok(stock);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/list")
    public List<Stock> listStock() {
        return stockService.findAll();
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Stock> getStockDetail(@PathVariable Long id) {
        try {
            Stock stock = stockService.findById(id);
            return ResponseEntity.ok(stock);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable Long id, @ModelAttribute StockDTO stockDTO) {
        try {
            Stock stock = stockService.updateStock(id, stockDTO);
            return ResponseEntity.ok(stock);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        try {
            stockService.deleteStock(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
