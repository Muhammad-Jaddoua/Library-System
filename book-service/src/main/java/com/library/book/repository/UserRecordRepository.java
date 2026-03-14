package com.library.book.repository;

import com.library.book.entity.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRecordRepository extends JpaRepository<UserRecord, Long> {
    boolean existsByUserId(Long userId);
}

