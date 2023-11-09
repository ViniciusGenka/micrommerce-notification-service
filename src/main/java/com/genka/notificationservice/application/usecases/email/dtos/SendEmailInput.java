package com.genka.notificationservice.application.usecases.email.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendEmailInput {
    String from;
    String to;
    String subject;
    String text;
}
