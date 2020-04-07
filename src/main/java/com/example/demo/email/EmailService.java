package com.example.demo.email;


import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${server.port}")
    private String value;


    public void sendVerificationMail( UUID url, String receiver ) {
        String verificationAddres = String.format( "http://localhost:" +
                "%s/users/verification?code=", value );
        SimpleMailMessage msg = new SimpleMailMessage();
        String subject = "Hesap Doğrulaması ";
        String emailbody = String.format( "Hesabınızı aktif etmek için lütfen tıklayınız %s%s", verificationAddres, url );
        msg.setSubject( "Digitus sistem doğrulaması" );
        msg.setTo( receiver );
        msg.setText( emailbody );
        javaMailSender.send( msg );


    }


}