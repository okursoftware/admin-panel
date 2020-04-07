package com.example.demo.user.customvalidation;

import com.example.demo.user.model.UserSignUpRequestModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor
public class PasswordMatchingValidation implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UserSignUpRequestModel.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserSignUpRequestModel object = (UserSignUpRequestModel) target;
        if (!object.getPassword().equals(object.getConfirmpassword())) {
            errors.rejectValue("password",null,"Parolalar Aynı Olmalı");
        }
    }
}
