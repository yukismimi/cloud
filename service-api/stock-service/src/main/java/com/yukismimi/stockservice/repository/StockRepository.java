package com.yukismimi.stockservice.repository;

import com.yukismimi.stockservice.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findAllByProductId(long productId);

    Stock findByProductIdAndSkuCode(long productId, String skuCode);
}
