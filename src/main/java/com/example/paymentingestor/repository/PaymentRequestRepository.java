package com.example.paymentingestor.repository;

import com.example.paymentingestor.entity.PaymentRequestEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRequestRepository extends MongoRepository<PaymentRequestEntity, String> {
}
