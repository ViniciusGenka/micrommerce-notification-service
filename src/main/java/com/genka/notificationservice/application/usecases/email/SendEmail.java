package com.genka.notificationservice.application.usecases.email;

import com.genka.notificationservice.application.usecases.email.dtos.SendEmailInput;

public interface SendEmail {
    void execute(SendEmailInput sendEmailInput);
}
