package com.library.library_system.model;

public class Book{
    private int ISBN;
    private String author;
    private String title;
    private String genre;
    private final String refCode;

    //Default Constructor
    public Book(){
        this.ISBN = 0000;
        this.author = "noAuthor";
        this.title = "noTitle";
        this.genre = "noGenre";
        this.refCode = generateReference();
    }
    //Initial values constructor
    public Book(int ISBN, String author, String title, String genre){
        this.ISBN = ISBN;
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.refCode = generateReference();
    }


    //Setters
    public void setISBN(int newISBN){
        this.ISBN = newISBN;
    }
    public void setAuthor(String newAuthor){
        this.author = newAuthor;
    }
    public void setTitle(String newTitle){
        this.title = newTitle;
    }
    public void setGenre(String newGenre){
        this.genre = newGenre;
    }

    //Getters
    public int getISBN(){
        return this.ISBN;
    }
    public String getAuthor(){
        return this.author;
    }
    public String getTitle(){
        return this.title;
    }
    public String getGenre(){
        return this.genre;
    }
    public String getRefCode(){
        return this.refCode;
    }

    //RefCode generator
    private String generateReference(){
        return ("" + this.author.charAt(0)+this.author.charAt(1) +"-"+ this.genre.charAt(0)+this.genre.charAt(1));
    }

//(Optional)ISBN verifier

    public boolean verifyISBN(int ISBN){
        int digit4 = ISBN % 10;
        int digit3 = (ISBN / 10) % 10;
        int digit2 = (ISBN / 100) % 10;
        int digit1 = (ISBN / 1000) % 10;
        return ((digit1*3 + digit2*2 + digit3)%4 == digit4);

    }

    public void printBookInfo(){
        System.out.println("Title: " + this.title);
        System.out.println("Author: " + this.author);
        System.out.println("ISBN: " + String.format("%0"+4+"d", this.ISBN) +" - "+ "Reference Code: " + this.refCode);
        System.out.println("Genre: " + this.genre);
    }

}