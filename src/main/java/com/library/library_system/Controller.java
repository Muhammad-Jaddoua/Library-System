package com.library.library_system;
import com.library.library_system.model.Book;
import com.library.library_system.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class Controller {

    @Autowired
    private LibraryService libraryService;

    @GetMapping("/books/isbn/{ISBN}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBook(@PathVariable int ISBN){
        return libraryService.getBook(ISBN);
    }

    @GetMapping("/books")
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<Book> getAllBooks(){
        return libraryService.getAllBooks();
    }

    @GetMapping("/books/genre/{genre}")
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<Book> getGenre(@PathVariable String genre){
        return libraryService.getGenre(genre);
    }

    @GetMapping("/books/author/{author}")
    @ResponseStatus(HttpStatus.OK)
    public int getNumOfBooksByAuthor(@PathVariable String author){
        return libraryService.getNumOfBooksByAuthor(author);
    }

    @GetMapping("/books/count")
    @ResponseStatus(HttpStatus.OK)
    public int getNumOfBooks(){
        return libraryService.getNumOfBooks();
    }


    @PostMapping("/library/books")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean addBook(@RequestBody int ISBN, String author, String title, String genre){
        return libraryService.addBook(ISBN, author, title, genre);
    }
    @PostMapping("/library/auto")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean autoAddBooks(){
        return libraryService.autoAddBooks();
    }
    @PostMapping("/library")
    @ResponseStatus(HttpStatus.OK)
    public String exitLibrary(){
        return libraryService.exitLibrary();
    }

    @DeleteMapping("/books/isbn/{ISBN}")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteBook(@PathVariable int ISBN){
        return libraryService.deleteBook(ISBN);
    }
}
