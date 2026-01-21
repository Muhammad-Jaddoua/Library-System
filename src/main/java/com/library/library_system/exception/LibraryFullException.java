package com.library.library_system.exception;

public class LibraryFullException extends RuntimeException {
    public LibraryFullException(String message){
        super(message);
    }

    public LibraryFullException(){
        super("Library full capacity");
    }
}
