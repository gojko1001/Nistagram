package com.xws.nistagrammonolith.service;

import com.xws.nistagrammonolith.domain.User;
import com.xws.nistagrammonolith.domain.UserCredentials;
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

    @Async
    public void verificationPassword(User user) throws MailException {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(mailSender);
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("Registration");
        String mailText = "Dear " + user.getFullName() + ",\n\n" +
                "Welcome to Ništagram \n\n" +
                "Your username is "+ user.getUsername() + "\n\n" +
                "You can verify your account here:" + "\t" +
                "http://localhost:3030/user/" + user.getUsername() + "\n\n" +
                "Best regards,";
        simpleMailMessage.setText(mailText);
        javaMailSender.send(simpleMailMessage);
    }

    @Async
    public void restartPassword(User user, UserCredentials userCredentials) throws MailException {
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
