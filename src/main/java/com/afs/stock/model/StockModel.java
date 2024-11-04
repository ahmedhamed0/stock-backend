package com.afs.stock.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.afs.stock.entity.Stock}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockModel implements Serializable {

    private Long id;
    @NotNull(message = "Name cannot be null")
    @NotEmpty
    private String name;
    private String description;
    @NotNull(message = "Current price cannot be null")
    private BigDecimal currentPrice;
    private LocalDateTime lastUpdate;
}