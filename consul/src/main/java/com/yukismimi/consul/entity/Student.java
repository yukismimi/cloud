package com.yukismimi.consul.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Student {

    @Id
    private long id;
    private String name;
    private int age;
}
