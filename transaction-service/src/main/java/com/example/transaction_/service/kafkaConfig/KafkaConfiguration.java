package com.example.transaction_.service.kafkaConfig;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfiguration {

    @Value("${kafka.topics.transaction}")
    private String transaction;

    @Bean
    public NewTopic topic(){

        return TopicBuilder
                .name(transaction)
                .build();
    }
}
