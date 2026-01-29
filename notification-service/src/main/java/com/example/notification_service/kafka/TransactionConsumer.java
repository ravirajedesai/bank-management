package com.example.notification_service.kafka;

import com.example.notification_service.dto.TransactionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TransactionConsumer {
    private static final Logger LOGGER=
            LoggerFactory.getLogger(TransactionConsumer.class);

    @KafkaListener(topics = "${kafka.topics.transaction}",
                    groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(TransactionEvent event){
    LOGGER.info("Transaction Done..{}",event);
    }


}
