package com.example.paymentingestor.config;

import com.example.paymentingestor.entity.IdempotencyKey;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

@Configuration
@RequiredArgsConstructor
public class MongoCollectionInitializer {
    private final MongoTemplate mongoTemplate;

    @Bean
    public CommandLineRunner createCollections() {
        return args -> {
            if (!mongoTemplate.collectionExists("accounts")) mongoTemplate.createCollection("accounts");
            if (!mongoTemplate.collectionExists("payment_requests")) mongoTemplate.createCollection("payment_requests");
            if (!mongoTemplate.collectionExists("idempotency_keys")) mongoTemplate.createCollection("idempotency_keys");
            mongoTemplate.indexOps(IdempotencyKey.class)
                    .ensureIndex(new Index().on("paymentId", Sort.Direction.ASC).unique());
        };
    }
}
