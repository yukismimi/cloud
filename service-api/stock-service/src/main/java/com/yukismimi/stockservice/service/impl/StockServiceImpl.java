package com.yukismimi.stockservice.service.impl;

import com.yukismimi.stockservice.entity.Stock;
import com.yukismimi.stockservice.repository.StockRepository;
import com.yukismimi.stockservice.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    StockRepository repository;

    @Override
    public void modifyStock(List<Stock> stockList) {
        repository.saveAll(stockList);
    }

    @Override
    public void addStock(long productId, List<Stock> stocks) {
        generateSkuCode(productId, stocks);
        repository.saveAll(stocks);
    }

    @Override
    public List<Stock> getStock(List<Long> skuIdList) {
        return repository.findAllById(skuIdList);
    }

    private void generateSkuCode(long productId, List<Stock> stocks) {
        if (CollectionUtils.isEmpty(stocks)) return;
        for (int i = 0; i < stocks.size(); i++) {
            Stock skuStock = stocks.get(i);
            if (StringUtils.isEmpty(skuStock.getSkuCode())) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                StringBuilder sb = new StringBuilder();
                //日期
                sb.append(sdf.format(new Date()));
                //四位商品id
                sb.append(String.format("%04d", productId));
                //三位索引id
                sb.append(String.format("%03d", i + 1));
                skuStock.setSkuCode(sb.toString());
            }
        }
    }
}
