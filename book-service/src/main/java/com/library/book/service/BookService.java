package com.library.book.service;

import com.library.book.exception.BookNotFoundException;
import com.library.book.exception.InvalidBook;
import com.library.book.entity.Book;
import com.library.book.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public boolean autoAddBooks(){
        addBook(200, "Ali", "Java", "Programming");
        addBook(1010, "Borat", "USA", "Documentary");
        addBook(1112, "Hitler", "Jewnation", "Cooking");
        addBook(2100, "ramsey", "donuts", "slavery");

        return true;
    }


    public boolean addBook(int ISBN, String author, String title, String genre) {
        Book newBook = new Book(ISBN, author, title, genre);
        return addBook(newBook);
    }
    public boolean addBook(Book newBook){
        // Check if ISBN is invalid OR book already exists
        if(!newBook.verifyISBN(newBook.getISBN()) || bookRepository.existsById(newBook.getISBN())){
            throw new InvalidBook(newBook.getISBN());
        }
        bookRepository.save(newBook);
        return true;

    }
    public boolean deleteBook(int ISBN){
        if(bookRepository.existsById(ISBN)){
            bookRepository.deleteById(ISBN);
            return true;
        }
        //book doesnt exist
        throw new BookNotFoundException(ISBN);        //book deleted


    }
    public Optional<Book> getBook(int ISBN){
        if (bookRepository.existsById(ISBN)){
            return bookRepository.findById((ISBN));

        } else {
            throw new BookNotFoundException(ISBN);
        }
    }
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    public List<Book> getGenre(String genre){
        return bookRepository.findByGenre(genre);
    }
    public int getNumOfBooksByAuthor(String author){
        return bookRepository.countByAuthor(author);
    }
    public Long getNumOfBooks(){
        return bookRepository.count();
    }
    public String exitLibrary(){
        return "Pretend this disconnected or something";
    }

}
