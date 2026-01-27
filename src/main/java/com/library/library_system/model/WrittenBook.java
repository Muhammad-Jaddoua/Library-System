package com.library.library_system.model;

import jakarta.persistence.Entity;

@Entity
public class WrittenBook extends Book{
    private final int numOfPages;
    
    public WrittenBook(int ISBN, String author, String title, String genre, int numOfPages){
        super(ISBN, author, title, genre);
        this.numOfPages = numOfPages;
    }
    public WrittenBook(){
        super();
        this.numOfPages = 0;
    }

    public int getNumOfPages(){
        return this.numOfPages;
    }
    
}