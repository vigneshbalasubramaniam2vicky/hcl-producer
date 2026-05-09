package com.example.paymentingestor.repository;

import com.example.paymentingestor.entity.IdempotencyKey;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IdempotencyKeyRepository extends MongoRepository<IdempotencyKey, String> {
    boolean existsByPaymentId(String paymentId);
}
