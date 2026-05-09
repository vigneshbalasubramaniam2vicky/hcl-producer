package com.example.paymentingestor.service;

import com.example.paymentingestor.dto.PaymentRequestDto;
import com.example.paymentingestor.entity.Account;
import com.example.paymentingestor.entity.IdempotencyKey;
import com.example.paymentingestor.exception.BusinessRuleException;
import com.example.paymentingestor.exception.DuplicatePaymentException;
import com.example.paymentingestor.exception.ResourceNotFoundException;
import com.example.paymentingestor.kafka.PaymentProducer;
import com.example.paymentingestor.repository.AccountRepository;
import com.example.paymentingestor.repository.IdempotencyKeyRepository;
import com.example.paymentingestor.repository.PaymentRequestRepository;
import com.example.paymentingestor.util.PaymentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {
    private final AccountRepository accountRepository;
    private final PaymentRequestRepository paymentRequestRepository;
    private final IdempotencyKeyRepository idempotencyKeyRepository;
    private final PaymentProducer paymentProducer;

    public void ingestPayment(PaymentRequestDto request) {
        if (idempotencyKeyRepository.existsByPaymentId(request.getPaymentId())) {
            throw new DuplicatePaymentException("Duplicate paymentId: " + request.getPaymentId());
        }
        Account debit = accountRepository.findById(request.getDebitAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("Debit account not found"));
        Account credit = accountRepository.findById(request.getCreditAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("Credit account not found"));

        if ("SUSPENDED".equalsIgnoreCase(debit.getStatus()) || "SUSPENDED".equalsIgnoreCase(credit.getStatus())) {
            throw new BusinessRuleException("Suspended account cannot be used for payment");
        }

        paymentRequestRepository.save(PaymentMapper.toEntity(request));
        idempotencyKeyRepository.save(IdempotencyKey.builder().paymentId(request.getPaymentId()).createdAt(Instant.now()).build());
        paymentProducer.publish(PaymentMapper.toEvent(request));
        log.info("Payment {} ingested successfully", request.getPaymentId());
    }
}
