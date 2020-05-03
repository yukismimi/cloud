package com.yukismimi.addressservice.controller;

import com.yukismimi.addressservice.dto.AddAddressDTO;
import com.yukismimi.addressservice.dto.ModifyAddressDTO;
import com.yukismimi.addressservice.entity.Address;
import com.yukismimi.addressservice.service.AddressService;
import com.yukismimi.common.api.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService service;

    @GetMapping("/all")
    public CommonResult<List<Address>> listAddresses() {
        return CommonResult.success(service.listAddresses());
    }

    @GetMapping("/get/{addressId}")
    public CommonResult<Address> getAddress(@PathVariable Long addressId) {
        return CommonResult.success(service.getAddress(addressId));
    }

    @PostMapping("/add")
    public CommonResult<String> addAddress(@RequestBody AddAddressDTO addAddressDto) {
        service.addAddress(addAddressDto);
        return CommonResult.success(null);
    }

    @PutMapping("/modify")
    public CommonResult<String> modifyAddress(@RequestBody ModifyAddressDTO modifyAddressDTO) {
        service.modifyAddress(modifyAddressDTO);
        return CommonResult.success(null);
    }

    @DeleteMapping("/delete")
    public CommonResult<String> deleteAddress(@RequestParam("id") Long id) {
        service.deleteAddress(id);
        return CommonResult.success(null);
    }
}
