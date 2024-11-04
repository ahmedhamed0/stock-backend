package com.afs.stock.service;

import com.afs.stock.entity.Stock;
import com.afs.stock.entity.StockExchange;
import com.afs.stock.mapper.StockExchangeMapper;
import com.afs.stock.mapper.StockMapper;
import com.afs.stock.model.StockExchangeModel;
import com.afs.stock.model.StockModel;
import com.afs.stock.repository.StockExchangeRepository;
import com.afs.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StockExchangeServiceImpl implements StockExchangeService {
    private final StockExchangeMapper stockExchangeMapper;

    private final StockExchangeRepository exchangeRepository;

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    // Retrieve all stock exchanges
    @Override
    public List<StockExchangeModel> findAll() {
        List<StockExchange> all = exchangeRepository.findAll();
        return stockExchangeMapper.toDto(all);
    }

    @Override
    public List<StockModel> retriveStocksByExchangeId(Long id) {
        StockExchange exchange = exchangeRepository.findById(id).orElseThrow();

          return exchange.getStocks().stream().map(stockMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<StockModel> retriveAvailableStocksByExchangeId(Long id) {
        StockExchange exchange = exchangeRepository.findById(id).orElseThrow();
        return  stockRepository.findAll().stream().filter(stock -> !exchange.getStocks().contains(stock)).map(stockMapper::toDto).collect(Collectors.toList());
    }


    // Retrieve a specific stock exchange by ID
    @Override
    public Optional<StockExchangeModel> findById(Long id) {
        return exchangeRepository.findById(id).map(stockExchangeMapper::toDto);
    }

    // Create a new stock exchange
    @Override
    public StockExchangeModel createStockExchange(StockExchangeModel stockExchange) {
        stockExchange.setLiveInMarket(false);  // Ensure liveInMarket is initially false
        return stockExchangeMapper.toDto(exchangeRepository.save(stockExchangeMapper.toEntity(stockExchange)));
    }

    // Update an existing stock exchange
    @Override
    public StockExchangeModel updateStockExchange(Long id, StockExchangeModel updatedExchange) {
        return stockExchangeMapper.toDto(exchangeRepository.findById(id).map(exchange -> {
            exchange.setName(updatedExchange.getName());
            exchange.setDescription(updatedExchange.getDescription());
            return exchangeRepository.save(exchange);
        }).orElseThrow(() -> new RuntimeException("StockExchange not found with id " + id)));
    }

    // Delete a stock exchange by ID
    @Override
    public void deleteStockExchange(Long id) {
        exchangeRepository.deleteById(id);
    }

    // Add a stock to the stock exchange and update liveInMarket status
    @Override
    public StockExchangeModel addStockToExchange(Long exchangeId, Long stockId) {
        StockExchange exchange = exchangeRepository.findById(exchangeId)
                .orElseThrow(() -> new RuntimeException("StockExchange not found with id " + exchangeId));
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new RuntimeException("Stock not found with id " + stockId));

        if (!exchange.getStocks().contains(stock)) {
            exchange.getStocks().add(stock);  // Add stock if it's not already in the list
        }

        exchange.updateLiveInMarketStatus(); // Update liveInMarket status
        return stockExchangeMapper.toDto(exchangeRepository.save(exchange));
    }

    // Remove a stock from the stock exchange and update liveInMarket status
    @Override
    public StockExchangeModel removeStockFromExchange(Long exchangeId, Long stockId) {
        StockExchange exchange = exchangeRepository.findById(exchangeId)
                .orElseThrow(() -> new RuntimeException("StockExchange not found with id " + exchangeId));
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new RuntimeException("Stock not found with id " + stockId));

        exchange.getStocks().remove(stock);  // Remove stock from the exchange
        exchange.updateLiveInMarketStatus(); // Update liveInMarket status
        return stockExchangeMapper.toDto(exchangeRepository.save(exchange));
    }


}
