package com.library.book.repository;

import com.library.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByGenre(String genre);
    List<Book> findByAuthor(String author);
    int countByAuthor(String author);
}
