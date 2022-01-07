package com.piug.piugbackend.controller;

import com.piug.piugbackend.common.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EmailController {

    private final JavaMailSender emailSender = getJavaMailSender();

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.mailtrap.io");
        mailSender.setPort(2525);
        mailSender.setUsername("8c480586a040e1");
        mailSender.setPassword("b455b2426a199a");

        return mailSender;
    }

    @PostMapping("/send")
    protected void sendEmail(@RequestBody Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email.getSender());
        message.setTo("marian.petrica@student.upt.ro");
        message.setSubject(email.getSubject());
        message.setText(email.getMessage());
        emailSender.send(message);
    }


}
