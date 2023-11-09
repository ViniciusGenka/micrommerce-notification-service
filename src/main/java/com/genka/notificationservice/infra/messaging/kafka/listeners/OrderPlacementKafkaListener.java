package com.genka.notificationservice.infra.messaging.kafka.listeners;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.genka.notificationservice.application.messaging.dtos.OrderPlacedEvent;
import com.genka.notificationservice.application.usecases.email.SendEmail;
import com.genka.notificationservice.application.usecases.email.dtos.SendEmailInput;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderPlacementKafkaListener {
    private final SendEmail sendEmailUseCase;
    private final ObjectMapper mapper;

    @Value("${spring.mail.username}")
    private String emailSenderAddress;

    public OrderPlacementKafkaListener(SendEmail sendEmailUseCase, ObjectMapper mapper) {
        this.sendEmailUseCase = sendEmailUseCase;
        this.mapper = mapper;
    }

    @KafkaListener(topics = "order_placed", groupId = "group-1")
    public void sendEmail(String event) {
        try {
            OrderPlacedEvent orderPlacedEvent = mapper.readValue(event, OrderPlacedEvent.class);
            SendEmailInput sendEmailInput = SendEmailInput.builder()
                    .from(this.emailSenderAddress)
                    .to(orderPlacedEvent.getBuyerEmailAddress())
                    .subject("Order realized successfully")
                    .text("Your order " + orderPlacedEvent.getOrderId() + " has been placed successfully!")
                    .build();
            this.sendEmailUseCase.execute(sendEmailInput);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
