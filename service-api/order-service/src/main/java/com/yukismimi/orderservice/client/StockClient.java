package com.yukismimi.orderservice.client;

import com.yukismimi.common.api.CommonResult;
import com.yukismimi.orderservice.client.dto.Stock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("stock-service")
public interface StockClient {

    @GetMapping("/stock/get")
    CommonResult<List<Stock>> getStock(@RequestParam("skuId") List<Long> skuIdList);

    @PutMapping("/stock/modify")
    CommonResult<String> modifyStock(@RequestBody List<Stock> stockList);
}
