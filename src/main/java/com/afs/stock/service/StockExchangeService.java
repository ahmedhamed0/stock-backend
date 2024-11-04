package com.afs.stock.service;

import com.afs.stock.model.StockExchangeModel;
import com.afs.stock.model.StockModel;

import java.util.List;
import java.util.Optional;

public interface StockExchangeService {
    // Retrieve all stock exchanges
    List<StockExchangeModel> findAll();

    List<StockModel> retriveStocksByExchangeId(Long id);

    List<StockModel> retriveAvailableStocksByExchangeId(Long id);

    // Retrieve a specific stock exchange by ID
    Optional<StockExchangeModel> findById(Long id);

    // Create a new stock exchange
    StockExchangeModel createStockExchange(StockExchangeModel stockExchange);

    // Update an existing stock exchange
    StockExchangeModel updateStockExchange(Long id, StockExchangeModel updatedExchange);

    // Delete a stock exchange by ID
    void deleteStockExchange(Long id);

    // Add a stock to the stock exchange and update liveInMarket status
    StockExchangeModel addStockToExchange(Long exchangeId, Long stockId);

    // Remove a stock from the stock exchange and update liveInMarket status
    StockExchangeModel removeStockFromExchange(Long exchangeId, Long stockId);
}
