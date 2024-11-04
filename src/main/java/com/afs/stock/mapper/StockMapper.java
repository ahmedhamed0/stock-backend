package com.afs.stock.mapper;

import com.afs.stock.entity.Stock;
import com.afs.stock.model.StockModel;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface StockMapper {


     Stock toEntity(StockModel stockModel);

     StockModel toDto(Stock stock);

    List<Stock> toEntity(List<StockModel> stockModel);

    List<StockModel> toDto(List<Stock> stock);


}
