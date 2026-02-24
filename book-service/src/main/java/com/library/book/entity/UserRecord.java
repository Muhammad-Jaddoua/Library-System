package com.library.book.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Local mirror of a user registered in auth-service.
 * Populated via Kafka event — keeps book-service DB independent.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_records")
public class UserRecord {

    @Id
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String role;

    public UserRecord(Long userId, String username, String role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }
}

