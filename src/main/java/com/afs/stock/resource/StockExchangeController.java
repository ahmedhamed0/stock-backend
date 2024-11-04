package com.afs.stock.resource;

import com.afs.stock.model.StockExchangeModel;
import com.afs.stock.model.StockModel;
import com.afs.stock.service.StockExchangeService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("stockExchanges")
@RequiredArgsConstructor
@OpenAPIDefinition(info = @Info(title = "StockExchange API", version = "v1"))
@SecurityRequirement(name = "BearerAuthentication")
public class StockExchangeController {

    private final StockExchangeService exchangeService;

    // List all stock exchanges
    @GetMapping
    public List<StockExchangeModel> getAllExchanges() {
        return exchangeService.findAll();
    }

    // Get a specific stock exchange by ID
    @GetMapping("/{id}")
    public ResponseEntity<StockExchangeModel> getExchangeById(@PathVariable Long id) {
        return exchangeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/{id}/stocks")
    public List<StockModel> getStocksByExchangeId(@PathVariable Long id) {
        return exchangeService.retriveStocksByExchangeId(id);
    }

    @GetMapping("/{id}/availablestocks")
    public List<StockModel> getAvailableStocksByExchangeId(@PathVariable Long id) {
        return exchangeService.retriveAvailableStocksByExchangeId(id);
    }


    // Create a new stock exchange
    @PostMapping
    public StockExchangeModel createExchange(@RequestBody StockExchangeModel exchange) {
        return exchangeService.createStockExchange(exchange);
    }

    // Update an existing stock exchange
    @PutMapping("/{id}")
    public ResponseEntity<StockExchangeModel> updateExchange(@PathVariable Long id, @RequestBody StockExchangeModel exchange) {
        try {
            return ResponseEntity.ok(exchangeService.updateStockExchange(id, exchange));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a stock exchange by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExchange(@PathVariable Long id) {
        exchangeService.deleteStockExchange(id);
        return ResponseEntity.noContent().build();
    }

    // Add a stock to the exchange
    @PostMapping("/{exchangeId}/stocks/{stockId}")
    public StockExchangeModel addStockToExchange(@PathVariable Long exchangeId, @PathVariable Long stockId) {
        return exchangeService.addStockToExchange(exchangeId, stockId);
    }

    // Remove a stock from the exchange
    @DeleteMapping("/{exchangeId}/stocks/{stockId}")
    public StockExchangeModel removeStockFromExchange(@PathVariable Long exchangeId, @PathVariable Long stockId) {
        return exchangeService.removeStockFromExchange(exchangeId, stockId);
    }
}
