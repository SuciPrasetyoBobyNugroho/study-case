package com.example.demo_studycase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo_studycase.dto.StockDTO;
import com.example.demo_studycase.model.Stock;
import com.example.demo_studycase.repository.StockRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    private static final List<String> ALLOWED_MIME_TYPES = Arrays.asList("image/jpeg", "image/png");

    public Stock createStock(StockDTO stockDTO) throws IOException {
        // Validasi MIME Type
        if (!ALLOWED_MIME_TYPES.contains(stockDTO.getGambarBarang().getContentType())) {
            throw new IllegalArgumentException("Format gambar tidak diizinkan, hanya JPG dan PNG.");
        }

        Stock stock = new Stock();
        stock.setNamaBarang(stockDTO.getNamaBarang());
        stock.setJumlahStokBarang(stockDTO.getJumlahStokBarang());
        stock.setNomorSeriBarang(stockDTO.getNomorSeriBarang());
        stock.setAdditionalInfo(stockDTO.getAdditionalInfo());
        stock.setCreatedBy(stockDTO.getCreatedBy());
        stock.setUpdatedBy(stockDTO.getUpdatedBy());

        // Simpan gambar sebagai path
        Path gambarPath = Files.write(Paths.get("uploads/" + stockDTO.getGambarBarang().getOriginalFilename()),
                stockDTO.getGambarBarang().getBytes());
        stock.setGambarPath(gambarPath.toString());

        return stockRepository.save(stock);
    }

    public List<Stock> findAll() {
        return stockRepository.findAll();
    }

    public Stock findById(Long id) {
        return stockRepository.findById(id).orElseThrow(() -> new RuntimeException("Stock not found"));
    }

    public Stock updateStock(Long id, StockDTO stockDTO) throws IOException {
        Stock stock = findById(id);
        stock.setNamaBarang(stockDTO.getNamaBarang());
        stock.setJumlahStokBarang(stockDTO.getJumlahStokBarang());
        stock.setNomorSeriBarang(stockDTO.getNomorSeriBarang());
        stock.setAdditionalInfo(stockDTO.getAdditionalInfo());
        stock.setUpdatedBy(stockDTO.getUpdatedBy());

        if (stockDTO.getGambarBarang() != null
                && ALLOWED_MIME_TYPES.contains(stockDTO.getGambarBarang().getContentType())) {
            Path gambarPath = Files.write(Paths.get("uploads/" + stockDTO.getGambarBarang().getOriginalFilename()),
                    stockDTO.getGambarBarang().getBytes());
            stock.setGambarPath(gambarPath.toString());
        }

        return stockRepository.save(stock);
    }

    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }
}
