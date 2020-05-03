package com.yukismimi.orderservice.client;

import com.yukismimi.common.api.CommonResult;
import com.yukismimi.orderservice.client.dto.CartItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("cart-service")
public interface CartClient {

    @GetMapping("/cart/all")
    CommonResult<List<CartItem>> getCartItems();

    @DeleteMapping("/cart/delete")
    CommonResult<String> deleteCartItems(@RequestParam Long ids);
}
