package com.yanye.springbootdemo.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class User {
    private int id;
    private String username;
    private int age;
    private String sex;
    private String className;
    @JsonIgnore
    private String createTime;
    @JsonIgnore
    private String updateTime;
    @JsonIgnore
    private String password;

}
