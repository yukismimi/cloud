package com.yukismimi.addressservice.service.impl;

import com.yukismimi.addressservice.dto.AddAddressDTO;
import com.yukismimi.addressservice.dto.ModifyAddressDTO;
import com.yukismimi.addressservice.entity.Address;
import com.yukismimi.addressservice.repository.AddressRepository;
import com.yukismimi.addressservice.service.AddressService;
import com.yukismimi.common.utils.SecurityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository repository;

    @Override
    public List<Address> listAddresses() {
        Long userId = SecurityUtil.getUserId();
        return repository.findAllByUserId(userId);
    }

    @Override
    public Address getAddress(Long addressId) {
        return repository.findById(addressId).orElseThrow();
    }

    @Override
    public void addAddress(AddAddressDTO addAddressDto) {
        Long userId = SecurityUtil.getUserId();
        Address address = new Address();
        BeanUtils.copyProperties(addAddressDto, address);
        address.setUserId(userId);
        repository.save(address);
    }

    @Override
    public void modifyAddress(ModifyAddressDTO modifyAddressDto) {
        repository.findById(modifyAddressDto.getId())
                .map(address -> {
                    BeanUtils.copyProperties(modifyAddressDto, address);
                    return address;
                })
                .map(repository::save);
    }

    @Override
    public void deleteAddress(Long id) {
        repository.deleteById(id);
    }
}
