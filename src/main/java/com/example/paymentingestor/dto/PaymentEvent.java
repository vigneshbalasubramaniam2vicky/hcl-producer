package com.example.paymentingestor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEvent {
    private String paymentId;
    private String debitAccountId;
    private String creditAccountId;
    private BigDecimal amount;
    private String currency;
    private String reference;
    private Instant timestamp;
}
