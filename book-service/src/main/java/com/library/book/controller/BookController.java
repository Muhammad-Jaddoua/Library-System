package com.library.book.controller;
import com.library.book.entity.Book;
import com.library.book.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService libraryService) {
        this.bookService = libraryService;
    }

    @GetMapping("/admin")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> getAdminControlPanel(){
        String html = "<html><body><h1>Admin Control Panel</h1><p>yo this is the control panel</p></body></html>";
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(html);
    }

    @GetMapping("/{ISBN}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Book> getBook(@PathVariable int ISBN){
        return bookService.getBook(ISBN);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/filter-genre/{genre}")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getGenre(@PathVariable String genre){
        return bookService.getGenre(genre);
    }

    @GetMapping("/count-author/{author}")
    @ResponseStatus(HttpStatus.OK)
    public int getNumOfBooksByAuthor(@PathVariable String author){
        return bookService.getNumOfBooksByAuthor(author);
    }

    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    public Long getNumOfBooks(){
        return bookService.getNumOfBooks();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public boolean addBook(@RequestParam int ISBN, @RequestParam String author, @RequestParam String title, @RequestParam String genre){
        return bookService.addBook(ISBN, author, title, genre);
    }

    @PostMapping("/auto")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean autoAddBooks(){
        return bookService.autoAddBooks();
    }

    @PostMapping("/exit")
    @ResponseStatus(HttpStatus.OK)
    public String exitLibrary(){
        return bookService.exitLibrary();
    }

    @DeleteMapping("/{ISBN}")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteBook(@PathVariable int ISBN){
        return bookService.deleteBook(ISBN);
    }
}
