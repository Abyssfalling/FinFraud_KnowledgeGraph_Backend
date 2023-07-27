package com.dhu.aml.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private int id;
    private String username;
    private String password;
    private int type;
    private int status;



}
