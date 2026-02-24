package com.library.auth.event;

// auth-service/src/main/java/com/library/auth/event/UserRegisteredEvent.java
public record UserRegisteredEvent(Long userId, String username, String role) {}
