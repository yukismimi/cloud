package com.yukismimi.userservice.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private int roleId;
    private int age;
    private int gender;
    private String mailAddress;
    private String password;
    private String otherInfo;
}
