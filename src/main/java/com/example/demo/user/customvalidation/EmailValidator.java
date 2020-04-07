package com.example.demo.user.customvalidation;


import com.example.demo.user.model.UserSignUpRequestModel;
import com.example.demo.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor
public class EmailValidator implements Validator {
    private final UserRepository userRepository;

    @Override
    public boolean supports( Class<?> clazz ) {
        return clazz.equals( UserSignUpRequestModel.class );
    }

    @Override
    public void validate( Object target, Errors errors ) {
        UserSignUpRequestModel target1 = (UserSignUpRequestModel) target;
        if ( target1.getEmail() != null && userRepository.existsByEmail( target1.getEmail() ) )
            errors.rejectValue( "email", null, "Bu e mail daha önce kaydolmuştur" );

    }
}
