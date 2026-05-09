package com.example.paymentingestor.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic paymentsSubmittedTopic() {
        return TopicBuilder.name("payments.submitted").partitions(6).replicas(1).build();
    }
}
