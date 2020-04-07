package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity()
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userServiceImp")
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;


    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http.authorizeRequests()
            .antMatchers( HttpMethod.POST, "/users", "/users/verification","users/*" )
            .permitAll().antMatchers( "/h2-console/**", "/users","/users/*" ).permitAll()
            .and().csrf().disable().formLogin().loginProcessingUrl( "/login" ).permitAll().and()
            .authorizeRequests()
            .anyRequest()
            .authenticated();


    }

    @Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
        auth.userDetailsService( userDetailsService ).passwordEncoder( bCryptPasswordEncoder );
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}