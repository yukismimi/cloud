package com.yukismimi.userservice.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String username;
    private String roles;
    private int age;
    private int gender;
    private String mailAddress;
    private String avatar;
    private String password;
    private String otherInfo;
}
