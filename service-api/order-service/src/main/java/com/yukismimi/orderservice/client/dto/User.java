package com.yukismimi.orderservice.client.dto;

import lombok.Data;

@Data
public class User {
    private int userId;
    private String username;
    private int age;
    private int gender;
    private String otherInfo;
}
