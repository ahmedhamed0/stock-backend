package com.afs.stock.resource;

import com.afs.stock.model.StockExchangeModel;
import com.afs.stock.model.StockModel;
import com.afs.stock.service.StockExchangeService;
import com.afs.stock.service.StockService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("stocks")
@RequiredArgsConstructor
@OpenAPIDefinition(info = @Info(title = "Stock API", version = "v1"))
@SecurityRequirement(name = "BearerAuthentication")
public class StockController {

    private final StockService stockService;

    // List all stocks
    @GetMapping
    public List<StockModel> getAllStocks() {
        return stockService.findAll();
    }

    // Get a specific stock by ID
    @GetMapping("/{id}")
    public ResponseEntity<StockModel> getStockById(@PathVariable Long id) {
        return stockService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new stock
    @PostMapping
    public StockModel createStock(@RequestBody StockModel stock) {
        return stockService.createStock(stock);
    }

    // Update stock details
    @PutMapping("/{id}")
    public ResponseEntity<StockModel> updateStock(@PathVariable Long id, @RequestBody StockModel stock) {
        try {
            return ResponseEntity.ok(stockService.updateStock(id, stock));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Update stock price
    @PutMapping("/{id}/price")
    public ResponseEntity<StockModel> updateStockPrice(@PathVariable Long id, @RequestBody StockModel stockModel) {
        try {
            return ResponseEntity.ok(stockService.updateStockPrice(id, stockModel.getCurrentPrice()));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a stock
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }

}
