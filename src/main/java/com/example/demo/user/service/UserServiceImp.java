package com.example.demo.user.service;


import com.example.demo.email.EmailService;
import com.example.demo.user.exception.UserNotFoundException;
import com.example.demo.user.model.UserDatabaseModel;
import com.example.demo.user.model.UserSignUpRequestModel;
import com.example.demo.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.jcache.interceptor.JCacheAspectSupport;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.CodingErrorAction;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder bcyrptPasswordencoder;
    private final EmailService emailService;

    public void createUser( UserSignUpRequestModel userSignUpRequestModel ) {
        UserDatabaseModel userDatabaseModel = new UserDatabaseModel();
        BeanUtils.copyProperties( userSignUpRequestModel, userDatabaseModel );
        userDatabaseModel.setEncryptedpassword( bcyrptPasswordencoder.encode( userSignUpRequestModel.getPassword() ) );
        UserDatabaseModel save = userRepository.save( userDatabaseModel );
        CompletableFuture.runAsync( () -> emailService.sendVerificationMail( save.getVerificationtoken(), save.getEmail() ) );

    }

    @Override
    @Transactional
    public void verificaUser( String code ) {
        // can throw illegal argument exception handle by controller advice
        UUID token = UUID.fromString( code );


        Optional<UserDatabaseModel> byVerificationtoken = userRepository.findByVerificationtoken( token );


        byVerificationtoken.orElseThrow(
                () -> new UserNotFoundException( "Bu tokena sahip kullanıcı bulunamadı" ) ).setIsenabled( true );
    }

    @Override
    public UserDetails loadUserByUsername( String email ) throws UsernameNotFoundException {
        Optional<UserDatabaseModel> byUsername = userRepository.findByEmail( email );

        return byUsername.orElseThrow( () -> new UsernameNotFoundException( "Kullanıcı Bulunamadı" ) );
    }
}
