package com.afs.stock.model;

import com.afs.stock.entity.Stock;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * DTO for {@link com.afs.stock.entity.StockExchange}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockExchangeModel implements Serializable {
    private Long id;
    @NotNull(message = "Name cannot be null")
    @NotEmpty
    private String name;
    private String description;
    @NotNull(message = "Live in market cannot be null")
    @NotEmpty
    private Boolean liveInMarket;

    private List<StockModel> stocks;
}