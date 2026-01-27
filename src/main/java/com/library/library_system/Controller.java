package com.library.library_system;
import com.library.library_system.model.Book;
import com.library.library_system.service.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/library")
public class Controller {

    private final LibraryService libraryService;

    public Controller(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/books/{ISBN}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Book> getBook(@PathVariable int ISBN){
        return libraryService.getBook(ISBN);
    }

    @GetMapping("/books")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllBooks(){
        return libraryService.getAllBooks();
    }

    @GetMapping("/books/filter-genre/{genre}")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getGenre(@PathVariable String genre){
        return libraryService.getGenre(genre);
    }

    @GetMapping("/books/count-author/{author}")
    @ResponseStatus(HttpStatus.OK)
    public int getNumOfBooksByAuthor(@PathVariable String author){
        return libraryService.getNumOfBooksByAuthor(author);
    }

    @GetMapping("/books/count")
    @ResponseStatus(HttpStatus.OK)
    public Long getNumOfBooks(){
        return libraryService.getNumOfBooks();
    }


    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean addBook(@RequestParam int ISBN,@RequestParam String author,@RequestParam String title,@RequestParam String genre){
        return libraryService.addBook(ISBN, author, title, genre);
    }
    @PostMapping("/books/auto")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean autoAddBooks(){
        return libraryService.autoAddBooks();
    }

    @PostMapping("/exit")
    @ResponseStatus(HttpStatus.OK)
    public String exitLibrary(){
        return libraryService.exitLibrary();
    }


    @DeleteMapping("/books/{ISBN}")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteBook(@PathVariable int ISBN){
        return libraryService.deleteBook(ISBN);
    }
}
