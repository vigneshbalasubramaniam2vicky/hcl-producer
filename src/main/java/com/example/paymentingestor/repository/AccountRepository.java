package com.example.paymentingestor.repository;

import com.example.paymentingestor.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {
}
