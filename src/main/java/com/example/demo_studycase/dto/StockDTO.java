package com.example.demo_studycase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDTO {

    private String namaBarang;
    private Integer jumlahStokBarang;
    private String nomorSeriBarang;
    private String additionalInfo;
    private MultipartFile gambarBarang;
    private String createdBy;
    private String updatedBy;
}
