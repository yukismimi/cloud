package com.yukismimi.cartservice.client;

import com.yukismimi.cartservice.client.dto.GetProductDTO;
import com.yukismimi.common.api.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("product-service")
public interface ProductClient {

    @GetMapping("/product/find")
    CommonResult<GetProductDTO> findById(@RequestParam("id") Long productId);
}
