package com.afs.stock.service;

import com.afs.stock.model.StockModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface StockService {
    // Retrieve all stocks
    List<StockModel> findAll();

    // Retrieve a specific stock by ID
    Optional<StockModel> findById(Long id);

    // Create a new stock
    StockModel createStock(StockModel stock);

    // Update an existing stock's details
    StockModel updateStock(Long id, StockModel updatedStock);

    // Delete a stock by ID
    void deleteStock(Long id);

    // Update the stock price and timestamp
    StockModel updateStockPrice(Long stockId, BigDecimal newPrice);
}
