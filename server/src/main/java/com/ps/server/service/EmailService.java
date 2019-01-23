package com.ps.server.service;


import com.ps.server.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private String FROM = "\tprzyjazne.szachy@gmail.com";

    @Async
    public void sendEmail(SimpleMailMessage email) {
        mailSender.send(email);
    }


    public void sendVerificationEmail(UserEntity user, String appUrl) {
        SimpleMailMessage verificationEmail = new SimpleMailMessage();
        verificationEmail.setFrom(FROM);
        verificationEmail.setTo(user.getEmail());
        verificationEmail.setSubject("Mail uwierzytelniający");
        verificationEmail.setText("Potwierdź swój e-mail:\n" + appUrl
                + "/verify?token=" + user.getVerificationToken());
        sendEmail(verificationEmail);
    }

    public void sendPasswordResetEmail(UserEntity user, String appUrl) {
        SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
        passwordResetEmail.setFrom(FROM);
        passwordResetEmail.setTo(user.getEmail());
        passwordResetEmail.setSubject("Reset hasła");
        passwordResetEmail.setText("Zresetuj swoje hasło:\n" + appUrl
                + "/reset?token=" + user.getVerificationToken());
        sendEmail(passwordResetEmail);
    }
}