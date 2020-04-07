package com.example.demo.user.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class UserLoginRequestModel implements Serializable {
    private String username;
    private String password;

}
