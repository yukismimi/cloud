package com.yukismimi.addressservice.dto;

import lombok.Data;

@Data
public class AddAddressDTO {

    private String name;
    private String phoneNumber;
    //是否默认
    private int defaultStatus;
    private String postCode;
    private String province;
    private String city;
    private String region;
    private String detailAddress;
}
