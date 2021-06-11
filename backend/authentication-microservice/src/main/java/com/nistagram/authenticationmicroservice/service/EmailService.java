package com.nistagram.authenticationmicroservice.service;

import com.nistagram.authenticationmicroservice.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String mailSender;

    private String verifyUrl = "https://localhost:8762/authentication-api/userCredentials/verify/";
    @Autowired
    private JwtService jwtService;

    @Async
    public void verificationPassword(String username, String email, String fullName) throws MailException {
        String jwt = jwtService.createToken(username);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(mailSender);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Registration");
        String url = verifyUrl + jwt;
        String mailText = "Dear " + fullName + ",\n\n" +
                "Welcome to Ništagram \n\n" +
                "Your username is " + username + "\n\n" +
                "You can verify your account here:" + "\t" +
                "<a href=\"" + url + "\">Verify your account</a>" + "\n\n" +
                "Best regards.";
        simpleMailMessage.setText(mailText);
        javaMailSender.send(simpleMailMessage);
    }

    /*
    public void resetPassword(User user) throws MailException {
        String jwt = jwtService.createToken(user.getUsername());
        String url = "https://localhost:3000/reset_password/" + jwt;
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(mailSender);
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("Restart password");
        String mailText = "Dear " + user.getFullName() + ",\n\n" +
                "Welcome to Ništagram" + "\n\n" +
                "<a href=\"" + url + "\">Reset password</a>" + "\n\n" +
                "Best regards,";
        simpleMailMessage.setText(mailText);
        javaMailSender.send(simpleMailMessage);
    }*/

}
