package com.afs.stock.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
public class StockExchange {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Boolean liveInMarket;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "stock_exchange_mapping",
            joinColumns = @JoinColumn(name = "stock_exchange_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "stock_id", referencedColumnName = "id")
    )
    private List<Stock> stocks;


    // Helper method to check if stock exchange can be live
    public void updateLiveInMarketStatus() {
        this.liveInMarket = this.stocks.size() >= 10;
    }


}
