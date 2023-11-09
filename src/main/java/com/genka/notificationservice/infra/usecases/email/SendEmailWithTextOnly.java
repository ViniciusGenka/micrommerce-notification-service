package com.genka.notificationservice.infra.usecases.email;


import com.genka.notificationservice.application.usecases.email.SendEmail;
import com.genka.notificationservice.application.usecases.email.dtos.SendEmailInput;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Primary
@Service
public class SendEmailWithTextOnly implements SendEmail {

    private final JavaMailSender mailSender;

    public SendEmailWithTextOnly(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void execute(SendEmailInput sendEmailInput) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sendEmailInput.getFrom());
        message.setTo(sendEmailInput.getTo());
        message.setText(sendEmailInput.getText());
        message.setSubject(sendEmailInput.getSubject());
        mailSender.send(message);
    }
}
