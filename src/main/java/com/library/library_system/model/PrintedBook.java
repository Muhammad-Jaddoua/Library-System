package com.library.library_system.model;

import jakarta.persistence.Entity;

@Entity
public class PrintedBook extends WrittenBook{
    private final boolean hardcover;

    public PrintedBook(int ISBN, String author, String title, String genre, int numOfPages, boolean hardcover){
        super(ISBN, author, title, genre, numOfPages);
        this.hardcover = hardcover;
    }   

    public PrintedBook(int ISBN, String author, String title, String genre, int numOfPages){
        super(ISBN, author, title, genre, numOfPages);
        this.hardcover = false;
    }
    public PrintedBook(){
        super();
        this.hardcover = false;
    }

    public String getCoverType(){
        return (this.hardcover)? "Hardcover": "Paperback";
    }
    @Override
    public void printBookInfo(){
        System.out.println("*****PrintedBook*****");
        super.printBookInfo();
        System.out.println("# of Pages: " + this.getNumOfPages());
        System.out.println("CoverTypre: " + this.getCoverType());
    }
}