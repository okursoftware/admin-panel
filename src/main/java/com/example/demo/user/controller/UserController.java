package com.example.demo.user.controller;


import com.example.demo.email.EmailService;
import com.example.demo.user.customvalidation.EmailValidator;
import com.example.demo.user.customvalidation.PasswordMatchingValidation;
import com.example.demo.user.model.UserLoginRequestModel;
import com.example.demo.user.model.UserSignUpRequestModel;
import com.example.demo.user.repository.UserRepository;
import com.example.demo.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Generated;
import org.dom4j.rule.Mode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@Controller
@AllArgsConstructor
@RequestMapping("users")
public class UserController {


    private final UserService userService;
    private final PasswordMatchingValidation passwordMatchingValidation;
    private final EmailValidator emailValidator;
    private final UserRepository userRepository;


    @InitBinder
    final protected void databinding( final WebDataBinder webDataBinder ) {
        webDataBinder.addValidators( passwordMatchingValidation, emailValidator );

    }



    @GetMapping("/verification")

    public String test( @RequestParam(required = true) String code ) {
        userService.verificaUser( code );
        return "Kullanıcı kaydı başarıyla tamamlandı";
    }

    @PostMapping
    public ResponseEntity createUser( @RequestBody @Valid UserSignUpRequestModel userSignUpRequestModel ) {

        userService.createUser( userSignUpRequestModel );
        return ResponseEntity.created( null ).build();
    }

}


