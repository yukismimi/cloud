package com.yukismimi.userservice.dto;

import lombok.Data;

@Data
public class UserDto {
    private int userId;
    private String username;
    private int age;
    private int gender;
    private String avatar;
    private String otherInfo;
}
