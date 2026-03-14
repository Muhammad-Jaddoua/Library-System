package com.library.book.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String message){
        super(message);
    }

    public BookNotFoundException(int ISBN){
        super("Book not found with ISBN:" + ISBN);
    }

}
