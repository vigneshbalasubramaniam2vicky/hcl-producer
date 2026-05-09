package com.example.paymentingestor.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class PaymentHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        return Health.up().withDetail("service", "payment-ingestor").build();
    }
}
