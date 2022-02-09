package com.kafka.example;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "kafka-example", groupId = "groupId")
    void Listener(String data){
        System.out.println("Received: " + data + " |");
    }
}
