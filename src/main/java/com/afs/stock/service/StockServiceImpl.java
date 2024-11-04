package com.afs.stock.service;

import com.afs.stock.entity.Stock;
import com.afs.stock.mapper.StockMapper;
import com.afs.stock.model.StockModel;
import com.afs.stock.repository.StockExchangeRepository;
import com.afs.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockMapper stockMapper;
    private final StockRepository stockRepository;
    private final StockExchangeRepository exchangeRepository;


    // Retrieve all stocks
    @Override
    public List<StockModel> findAll() {
        return stockMapper.toDto(stockRepository.findAll());
    }

    // Retrieve a specific stock by ID
    @Override
    public Optional<StockModel> findById(Long id) {
        return stockRepository.findById(id).map(stockMapper::toDto);
    }

    // Create a new stock
    @Override
    public StockModel createStock(StockModel stock) {
        stock.setLastUpdate(LocalDateTime.now());  // Set the initial lastUpdate timestamp
        return stockMapper.toDto(stockRepository.save(stockMapper.toEntity(stock)));
    }

    // Update an existing stock's details
    @Override
    public StockModel updateStock(Long id, StockModel updatedStock) {
        return stockMapper.toDto(stockRepository.findById(id).map(stock -> {
            stock.setName(updatedStock.getName());
            stock.setDescription(updatedStock.getDescription());
            stock.setCurrentPrice(updatedStock.getCurrentPrice());
            return stockRepository.save(stock);
        }).orElseThrow(() -> new RuntimeException("Stock not found with id " + id)));
    }

    // Delete a stock by ID
    @Override
    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }

    // Update the stock price and timestamp
    @Override
    public StockModel updateStockPrice(Long stockId, BigDecimal newPrice) {
        if(stockId == null || newPrice == null) {
            throw new RuntimeException("Nullabe Data");
        }

        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new RuntimeException("Stock not found with id " + stockId));

        stock.setCurrentPrice(newPrice);
        stock.setLastUpdate(LocalDateTime.now());  // Update last update timestamp
        return stockMapper.toDto(stockRepository.save(stock));
    }

}
