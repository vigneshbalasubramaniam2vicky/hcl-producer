package com.example.paymentingestor.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "payment_requests")
public class PaymentRequestEntity {
    @Id
    private String id;
    private String paymentId;
    private String debitAccountId;
    private String creditAccountId;
    private BigDecimal amount;
    private String currency;
    private String reference;
    private Instant timestamp;
    private Instant ingestedAt;
}
