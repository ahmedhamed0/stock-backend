package com.afs.stock.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Stock {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal currentPrice;
    private LocalDateTime lastUpdate;

    @ManyToMany(mappedBy = "stocks", fetch = FetchType.LAZY)
    private List<StockExchange> stockExchanges;
}
