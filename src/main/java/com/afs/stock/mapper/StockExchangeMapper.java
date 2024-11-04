package com.afs.stock.mapper;

import com.afs.stock.entity.Stock;
import com.afs.stock.entity.StockExchange;
import com.afs.stock.model.StockExchangeModel;
import com.afs.stock.model.StockModel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface StockExchangeMapper {

     StockExchange toEntity(StockExchangeModel stockExchangeModel);

     StockExchangeModel toDto(StockExchange stockExchange);


     List<StockExchange> toEntity(List<StockExchangeModel> stockExchangeModel);

     List<StockExchangeModel> toDto(List<StockExchange> stockExchange);
}
