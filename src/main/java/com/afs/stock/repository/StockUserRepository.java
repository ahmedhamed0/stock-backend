package com.afs.stock.repository;

import com.afs.stock.entity.StockUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockUserRepository extends JpaRepository<StockUser, Integer> {

    Optional<StockUser> findByUsername(String username);

}