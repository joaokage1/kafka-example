package com.example.kafkaexample.listener;

import com.example.kafkaexample.dto.MessageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaListeners {

    @KafkaListener(topics = "kafka-topic-1", groupId = "group-id-01")
    void listener(MessageRequest data) {
        log.info("Listening: {}", data);
    }
}
