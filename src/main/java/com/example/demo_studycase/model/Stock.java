package com.example.demo_studycase.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "stocks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String namaBarang;

    @Column(nullable = false)
    private Integer jumlahStokBarang;

    @Column(nullable = false)
    private String nomorSeriBarang;

    @Column(columnDefinition = "jsonb")
    private String additionalInfo;

    @Column(nullable = true)
    private String gambarPath; 

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String createdBy;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column
    private String updatedBy;
}
