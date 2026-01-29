package com.example.transaction_.service.kafka;

import com.example.transaction_.service.dto.TransactionEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {

    private final static Logger LOGGER=
            LoggerFactory.getLogger(KafkaProducer.class);

    @Value("${kafka.topics.transaction}")
    private String transaction;

    private final KafkaTemplate<String,TransactionEvent> kafkaTemplate;

    public void sentMessage(TransactionEvent event){

        LOGGER.info("Transaction Done..{}",event);

        Message<TransactionEvent> message= MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC,transaction)
                .build();

        kafkaTemplate.send(message);
    }
}
