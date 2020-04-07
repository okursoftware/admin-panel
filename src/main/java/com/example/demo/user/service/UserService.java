package com.example.demo.user.service;


import com.example.demo.user.model.UserSignUpRequestModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public void createUser( UserSignUpRequestModel userSignUpRequestModel );

    void verificaUser( String code );
}
