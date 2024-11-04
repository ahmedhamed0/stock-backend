package com.afs.stock.repository;

import com.afs.stock.entity.StockExchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockExchangeRepository extends JpaRepository<StockExchange, Long> {

}
