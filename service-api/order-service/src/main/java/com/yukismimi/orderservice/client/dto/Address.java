package com.yukismimi.orderservice.client.dto;

import lombok.Data;

@Data
public class Address {

    private Long id;
    private long userId;
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
