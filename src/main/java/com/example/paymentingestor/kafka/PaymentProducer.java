package com.example.paymentingestor.kafka;

import com.example.paymentingestor.dto.PaymentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentProducer {
    private static final String TOPIC = "payments.submitted";
    private final KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    public void publish(PaymentEvent event) {
        kafkaTemplate.send(TOPIC, event.getDebitAccountId(), event);
        log.info("Published payment {} to topic {}", event.getPaymentId(), TOPIC);
    }
}
