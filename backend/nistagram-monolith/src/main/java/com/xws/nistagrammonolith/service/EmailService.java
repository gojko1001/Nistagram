package com.xws.nistagrammonolith.service;

import com.xws.nistagrammonolith.domain.User;
import com.xws.nistagrammonolith.domain.UserCredentials;
import com.xws.nistagrammonolith.security.JwtService;
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

    private String verifyUrl= "https://localhost:3030/userCredentials/verify/";
    @Autowired
    private JwtService jwtService;
    @Async
    public void verificationPassword(User user) throws MailException {
        String jwt = jwtService.createToken(user.getUsername()/*, Role.ROLE_USER*/);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(mailSender);
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("Registration");
        String url= verifyUrl + jwt;
        String mailText = "Dear " + user.getFullName() + ",\n\n" +
                "Welcome to Ništagram \n\n" +
                "Your username is "+ user.getUsername() + "\n\n" +
                "You can verify your account here:" + "\t" +
                "<a href=\"" + url + "\">Verify your account</a>" + "\n\n" +
                "Best regards.";
        simpleMailMessage.setText(mailText);
        javaMailSender.send(simpleMailMessage);
    }

    @Async
    public void sendPassword(User user, UserCredentials userCredentials) throws MailException {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(mailSender);
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("Restart password");
        String mailText = "Dear " + user.getFullName() + ",\n\n" +
                "Welcome to Ništagram" + "\n\n" +
                "Your password is "+ userCredentials.getPassword() + "\n\n" +
                "Best regards,";
        simpleMailMessage.setText(mailText);
        javaMailSender.send(simpleMailMessage);
    }
}
