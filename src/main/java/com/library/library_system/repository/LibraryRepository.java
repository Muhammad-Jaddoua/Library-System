package com.library.library_system.repository;

import com.library.library_system.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {
    // Additional query methods can be defined here if needed
}