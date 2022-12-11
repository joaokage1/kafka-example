package com.example.kafkaexample.config;

import com.example.kafkaexample.dto.MessageRequest;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String boostrapServers;

    public Map<String, Object> consumerConfig() {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, boostrapServers);
        return properties;
    }

    @Bean
    public ConsumerFactory<String, MessageRequest> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(this.consumerConfig(), StringDeserializer::new, JsonDeserializer::new);
    }

    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, MessageRequest>> factory(ConsumerFactory<String, MessageRequest> consumerFactory){
        ConcurrentKafkaListenerContainerFactory<String,MessageRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}
