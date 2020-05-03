package com.yukismimi.stockservice.controller;

import com.yukismimi.common.api.CommonResult;
import com.yukismimi.stockservice.entity.Stock;
import com.yukismimi.stockservice.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    StockService service;

    @GetMapping("/get")
    public CommonResult<List<Stock>> getStock(@RequestParam("skuId") List<Long> skuIdList) {
        List<Stock> stockList = service.getStock(skuIdList);
        return CommonResult.success(stockList);
    }

    @PostMapping("/add")
    public CommonResult<String> addStock(long productId, List<Stock> stocks) {
        service.addStock(productId, stocks);
        return CommonResult.success(null);
    }

    @PutMapping("/modify")
    public CommonResult<String> modifyStock(@RequestBody List<Stock> stockList) {
        service.modifyStock(stockList);
        return CommonResult.success(null);
    }
}
