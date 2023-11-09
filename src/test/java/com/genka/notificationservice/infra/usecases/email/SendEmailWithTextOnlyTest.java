package com.genka.notificationservice.infra.usecases.email;

import com.genka.notificationservice.application.usecases.email.dtos.SendEmailInput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SendEmailWithTextOnlyTest {
    @Mock
    private JavaMailSender mailSenderMock;
    @InjectMocks
    private SendEmailWithTextOnly sut;

    @Test
    @DisplayName("It should call the JavaMailSender passing a SimpleMailMessage based on the SendEmailInput")
    void sendEmail() {
        SendEmailInput input = SendEmailInput.builder()
                .from("email@from.com")
                .to("email@to.com")
                .subject("any subject")
                .text("any text")
                .build();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(input.getFrom());
        message.setTo(input.getTo());
        message.setText(input.getText());
        message.setSubject(input.getSubject());
        sut.execute(input);
        verify(mailSenderMock, times(1)).send(message);
    }
}