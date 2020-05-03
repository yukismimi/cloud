package com.yukismimi.orderservice.client;

import com.yukismimi.common.api.CommonResult;
import com.yukismimi.orderservice.client.dto.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("address-service")
public interface AddressClient {

    @GetMapping("/address/get/{addressId}")
    CommonResult<Address> getAddress(@PathVariable Long addressId);

    @GetMapping("/address/all")
    CommonResult<List<Address>> listAddresses();
}
