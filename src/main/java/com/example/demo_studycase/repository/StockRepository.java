package com.example.demo_studycase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo_studycase.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
}

