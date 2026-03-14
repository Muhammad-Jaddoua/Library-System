package com.library.book.kafka;

import com.library.book.entity.UserRecord;
import com.library.book.event.UserRegisteredEvent;
import com.library.book.repository.UserRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

// book-service/src/main/java/com/library/book/kafka/AuthEventConsumer.java
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthEventConsumer {

    private final UserRecordRepository userRecordRepository;

    @KafkaListener(topics = "auth-topic", groupId = "book-service-group")
    public void handleUserRegistered(UserRegisteredEvent event) {
        log.info("New user registered: {}, syncing to book-service DB", event.userId());
        if (!userRecordRepository.existsByUserId(event.userId())) {
            userRecordRepository.save(new UserRecord(event.userId(), event.username(), event.role()));
            log.info("UserRecord saved for userId={}", event.userId());
        } else {
            log.warn("UserRecord already exists for userId={}, skipping", event.userId());
        }
    }
}
