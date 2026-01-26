package com.library.library_system.model;

import jakarta.persistence.Entity;

@Entity
public class AudioBook extends Book{
    final private int duration;
    final private String narrator;

    public AudioBook(int ISBN, String author, String title, String genre, int duration, String narrator){
        super(ISBN, author, title, genre);
        this.duration = duration;
        this.narrator = narrator;
    }

    public int getDuration(){
        return this.duration;
    }
    public String getNarrator(){
        return this.narrator;
    }

    @Override
    public void printBookInfo(){
        System.out.println("*****AudioBook*****");
        super.printBookInfo();
        System.out.println("Duration: " + this.duration);
        System.out.println("Narrator: " + this.narrator);
    }

}
// *****AudioBook*****
// •Title: title
// Author: author
// ISBN: ISBN - Reference Code : refCode
// Genre: genre
// Duration: duration
// Narrator: narrator