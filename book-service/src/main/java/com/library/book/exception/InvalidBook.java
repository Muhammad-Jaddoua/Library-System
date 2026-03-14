package com.library.book.exception;

public class InvalidBook extends RuntimeException {

    public InvalidBook(String message){
        super(message);
    }

    public InvalidBook(int ISBN){
        super("Book had an invalid ISBN or already exists:" + ISBN);
    }

}
