package com.example.paymentingestor.util;

import com.example.paymentingestor.dto.PaymentEvent;
import com.example.paymentingestor.dto.PaymentRequestDto;
import com.example.paymentingestor.entity.PaymentRequestEntity;

import java.time.Instant;

public final class PaymentMapper {
    private PaymentMapper() {}

    public static PaymentEvent toEvent(PaymentRequestDto dto) {
        return PaymentEvent.builder()
                .paymentId(dto.getPaymentId())
                .debitAccountId(dto.getDebitAccountId())
                .creditAccountId(dto.getCreditAccountId())
                .amount(dto.getAmount())
                .currency(dto.getCurrency())
                .reference(dto.getReference())
                .timestamp(dto.getTimestamp())
                .build();
    }

    public static PaymentRequestEntity toEntity(PaymentRequestDto dto) {
        return PaymentRequestEntity.builder()
                .paymentId(dto.getPaymentId())
                .debitAccountId(dto.getDebitAccountId())
                .creditAccountId(dto.getCreditAccountId())
                .amount(dto.getAmount())
                .currency(dto.getCurrency())
                .reference(dto.getReference())
                .timestamp(dto.getTimestamp())
                .ingestedAt(Instant.now())
                .build();
    }
}
