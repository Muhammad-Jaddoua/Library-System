package com.library.auth.kafka;

import com.library.auth.event.UserRegisteredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

// auth-service/src/main/java/com/library/auth/kafka/AuthEventProducer.java
@Service
@RequiredArgsConstructor
public class AuthEventProducer {

    private final KafkaTemplate<String, UserRegisteredEvent> kafkaTemplate;
    private static final String TOPIC = "auth-topic";

    public void publishUserRegistered(UserRegisteredEvent event) {
        kafkaTemplate.send(TOPIC, event);
    }
}
