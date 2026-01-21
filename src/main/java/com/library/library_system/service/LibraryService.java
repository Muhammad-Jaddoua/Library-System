package com.library.library_system.service;

import com.library.library_system.exception.BookNotFoundException;
import com.library.library_system.exception.InvalidBook;
import com.library.library_system.model.Book;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class LibraryService {
    private final Book[] libraryBooks;
    private int numOfBooks;
    public static final int MAX_SIZE = 8;

    public LibraryService(){
        this.libraryBooks = new Book[MAX_SIZE];
        this.numOfBooks = 0;
    }


    public void printBookByIndex(int index){
        this.libraryBooks[index].printBookInfo();
    }

    public boolean autoAddBooks(){
        addBook(0200, "ali", "Java", "Programming");
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
        if(!newBook.verifyISBN(newBook.getISBN()) || findBook(newBook) >= 0){
            throw new InvalidBook(newBook.getISBN());
        }

        // Find first available slot
        for(int idx = 0; idx < this.libraryBooks.length; idx++){
            if(this.libraryBooks[idx] == null){
                this.libraryBooks[idx] = newBook;
                this.numOfBooks += 1;
                return true;
            }
        }
        //array is full
        return false;
    }
    public boolean deleteBook(int ISBN){
        int deleteTargetIdx = this.findBook(ISBN);
        if(deleteTargetIdx<0){
            //book doesnt exist
            throw new BookNotFoundException(ISBN);
        }
        this.libraryBooks[deleteTargetIdx] = null;
        //book deleted
        return true;

    }
    public int findBook(int ISBN){
        for (int idx = 0; idx < this.libraryBooks.length; idx++) {
            if(this.libraryBooks[idx] != null && this.libraryBooks[idx].getISBN() == ISBN){
                return idx;
            }
        }
        return -1;
    }
    public int findBook(Book book){
        for (int idx = 0; idx < this.libraryBooks.length; idx++) {
            if(this.libraryBooks[idx] != null && this.libraryBooks[idx].getISBN() == book.getISBN()){
                return idx;
            }
        }
        return -1;
    }
    public Book getBook(int ISBN){
        int idx = findBook(ISBN);
        if (idx < 0){
            throw new BookNotFoundException(ISBN);
        }
        return libraryBooks[findBook(ISBN)];
    }
    public ArrayList<Book> getAllBooks(){
        ArrayList<Book> availableBookList = new ArrayList<>();
        for (Book book : this.libraryBooks) {
            if (book != null) {
                availableBookList.add(book);
            }
        }
        return availableBookList;
    }
    public ArrayList<Book> getGenre(String genre){
        ArrayList<Book> availableBookList = new ArrayList<>();
        for (Book book : this.libraryBooks) {
            if (book != null) {
                if(book.getGenre().equals(genre)){
                    availableBookList.add(book);
                }
            }
        }
        return availableBookList;
    }
    public int getNumOfBooksByAuthor(String author){
        int count = 0;
        for (Book book : this.libraryBooks) {
            if (book != null) {
                if(book.getAuthor().equals(author)){
                    count +=1;
                }
            }

        }
        return count;
    }
    public int getNumOfBooks(){
        return this.numOfBooks;
    }
    public String exitLibrary(){
        return "Pretend this disconnected or something";
    }

}
