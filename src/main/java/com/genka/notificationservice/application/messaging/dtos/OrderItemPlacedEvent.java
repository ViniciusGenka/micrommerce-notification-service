package com.genka.notificationservice.application.messaging.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemPlacedEvent {
    UUID productId;
    Integer quantity;
    String name;
}
