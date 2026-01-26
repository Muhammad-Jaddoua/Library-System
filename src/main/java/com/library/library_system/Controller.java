package com.library.library_system;
import com.library.library_system.model.Book;
import com.library.library_system.service.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class Controller {

    private final LibraryService libraryService;

    public Controller(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/library/books/{ISBN}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBook(@PathVariable int ISBN){
        return libraryService.getBook(ISBN);
    }

    @GetMapping("/library/books")
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<Book> getAllBooks(){
        return libraryService.getAllBooks();
    }

    @GetMapping("/library/books/filter-genre/{genre}")
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<Book> getGenre(@PathVariable String genre){
        return libraryService.getGenre(genre);
    }

    @GetMapping("/library/books/filter-author/{author}")
    @ResponseStatus(HttpStatus.OK)
    public int getNumOfBooksByAuthor(@PathVariable String author){
        return libraryService.getNumOfBooksByAuthor(author);
    }

    @GetMapping("/library/books/count")
    @ResponseStatus(HttpStatus.OK)
    public int getNumOfBooks(){
        return libraryService.getNumOfBooks();
    }


    @PostMapping("/library/books")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean addBook(@RequestParam int ISBN,@RequestParam String author,@RequestParam String title,@RequestParam String genre){
        return libraryService.addBook(ISBN, author, title, genre);
    }
    @PostMapping("/library/auto")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean autoAddBooks(){
        return libraryService.autoAddBooks();
    }

    @PostMapping("/library/exit")
    @ResponseStatus(HttpStatus.OK)
    public String exitLibrary(){
        return libraryService.exitLibrary();
    }


    @DeleteMapping("/library/books/{ISBN}")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteBook(@PathVariable int ISBN){
        return libraryService.deleteBook(ISBN);
    }
}
