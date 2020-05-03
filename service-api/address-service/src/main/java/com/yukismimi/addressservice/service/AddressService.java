package com.yukismimi.addressservice.service;

import com.yukismimi.addressservice.dto.AddAddressDTO;
import com.yukismimi.addressservice.dto.ModifyAddressDTO;
import com.yukismimi.addressservice.entity.Address;

import java.util.List;

public interface AddressService {

    List<Address> listAddresses();

    Address getAddress(Long addressId);

    void addAddress(AddAddressDTO addAddressDto);

    void modifyAddress(ModifyAddressDTO modifyAddressDto);

    void deleteAddress(Long id);
}
