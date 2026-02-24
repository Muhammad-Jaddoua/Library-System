package com.library.book.entity;

public class Library{
    private final Book[] libraryBooks;
    private int numOfBooks;
    public static final int MAX_SIZE = 8;

    public Library(){
        this.libraryBooks = new Book[MAX_SIZE];
        this.numOfBooks = 0;
    }

    public void printBookByIndex(int index){
        this.libraryBooks[index].printBookInfo();
    }

    public boolean addBook(int ISBN, String author, String title, String genre){
        Book newBook = new Book(ISBN, author, title, genre);
        // Check if ISBN is invalid OR book already exists
        if(!newBook.verifyISBN(ISBN) || findBook(newBook) >= 0){
            return false;
        }
        
        // Find first available slot
        for(int idx = 0; idx < this.libraryBooks.length; idx++){
            if(this.libraryBooks[idx] == null){
                this.libraryBooks[idx] = newBook;
                this.numOfBooks += 1;
                return true;
            }
        }
        
        // Library is full
        return false;
    }

    public boolean addBook(Book newBook){
        // Check if ISBN is invalid OR book already exists
        if(!newBook.verifyISBN(newBook.getISBN()) || findBook(newBook) >= 0){
            return false;
        }
        
        // Find first available slot
        for(int idx = 0; idx < this.libraryBooks.length; idx++){
            if(this.libraryBooks[idx] == null){
                this.libraryBooks[idx] = newBook;
                this.numOfBooks += 1;
                return true;
            }
        }
        
        // Library is full
        return false;
    }

    public void deleteBook(int ISBN){
        int deleteTargetIdx = findBook(ISBN);
        if(deleteTargetIdx<0){
            System.out.println("Book doesn't exist");
            return;
        }
        this.libraryBooks[deleteTargetIdx] = null;   
        System.out.println("Book deleted successfully!");

        // for(int idx = 0; idx<this.libraryBooks.length; idx++){
        //     if(this.libraryBooks[idx] != null && this.libraryBooks[idx+1] == null){
        //         this.libraryBooks[deleteTargetIdx] = this.libraryBooks[idx];
        //         System.out.println("Book deleted successfully!");
        //         return;
        //     }
        // }
    }
    
    public int findBook(int ISBN){
        for (int idx = 0; idx < this.libraryBooks.length; idx++) {
            if(this.libraryBooks[idx] != null && this.libraryBooks[idx].getISBN() == ISBN){
                return idx;
            }      
        }
        return -1;
    }


    public int findBook(Book currentBook){
        for (int idx = 0; idx < this.libraryBooks.length; idx++) {
            if(this.libraryBooks[idx] != null && this.libraryBooks[idx].getISBN() == currentBook.getISBN()){
                return idx;
            }        
        }
        return -1;
    }

    public void printAll(){
        for (Book book : this.libraryBooks) {
            if (book != null) {
                book.printBookInfo();
            }
            
            
        }
    }

    public void printGenre(String genre){
        for (Book book : this.libraryBooks) {
            if (book != null) {
                if(book.getGenre().equals(genre)){
                    book.printBookInfo();
                }
            }
            
            
        }
    }

    public int getNumberOfBooksByAuthor(String author){
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

    public int getNumberOfBooks(){
        return this.numOfBooks;
    }






}