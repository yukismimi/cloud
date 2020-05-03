package com.yukismimi.stockservice.service;

import com.yukismimi.stockservice.entity.Stock;

import java.util.List;

public interface StockService {

    void modifyStock(List<Stock> stockList);

    void addStock(long productId, List<Stock> stocks);

    List<Stock> getStock(List<Long> skuIdList);
}
