package com.yukismimi.gateway.client.dto;

import lombok.Data;

@Data
public class UserDTO {
    private int userId;
    private String username;
    private int age;
    private int gender;
    private String avatar;
    private String otherInfo;
}
