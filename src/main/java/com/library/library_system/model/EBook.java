package com.library.library_system.model;

import jakarta.persistence.Entity;

@Entity
public class EBook extends WrittenBook{
    final int size;

    public EBook(int ISBN, String author, String title, String genre, int numOfPages, int size){
        super(ISBN, author, title, genre, numOfPages);
        this.size = size;
    }

    public int getSize(){
        return this.size;
    }

    @Override
    public void printBookInfo(){
        System.out.println("*****EBook*****");
        super.printBookInfo();
        System.out.println("# of Page: " + this.getNumOfPages());
        System.out.println("Book Size: " + this.size);
    }
}