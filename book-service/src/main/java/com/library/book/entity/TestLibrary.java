package com.library.book.entity;

import java.util.Scanner;
public class TestLibrary{
    private static void printMenu(){
        System.out.println("\n");
        System.out.println("**********************************************************************");
        System.out.println("1) Add a book");
        System.out.println("2) Delete a book");
        System.out.println("3) Find a book");
        System.out.println("4) List all books");
        System.out.println("5) List books for a given genre");
        System.out.println("6) Number of books for a given author");
        System.out.println("7) Total number of books.");
        System.out.println("8) Exit");
        System.out.println("**********************************************************************");
        System.out.println("Enter your option:>");  
    }
    


    
    public static void main(String[] args) {

        Library freshLibrary = new Library();
        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        while(run){
            printMenu();
            int userChoice = scanner.nextInt();
            scanner.nextLine();
            switch(userChoice){
                case 1 -> {
                    System.out.println("Please, enter the book details (ISBN author title genre) separated by spaces:");
                    String[]bookInfo = scanner.nextLine().trim().split("\\s+");
                    if(bookInfo.length >= 4){
                        boolean addCheck = freshLibrary.addBook(Integer.parseInt(bookInfo[0]), bookInfo[1], bookInfo[2], bookInfo[3]);
                        if(addCheck){
                            System.out.println("Book added successfully!");
                        }else{
                            System.out.println("Invalid ISBN or book already exists.");
                        }
                    }else{
                        System.out.println("Invalid input format. Please enter: ISBN author title genre");
                    }
                }
                case 2 -> {
                    System.out.println("Please, enter the book's ISBN: ");
                    int ISBN = scanner.nextInt();
                    scanner.nextLine();
                    freshLibrary.deleteBook(ISBN);
                }
                case 3 -> {
                    System.out.println("Please, enter the book's ISBN: ");
                    int ISBN = scanner.nextInt();
                    scanner.nextLine();
                    int idx = freshLibrary.findBook(ISBN);
                    freshLibrary.printBookByIndex(idx);
                }
                case 4 -> {
                    System.out.println("All books available in the library: ");
                    freshLibrary.printAll();
                }
                case 5 -> {
                    System.out.println("Please, enter the requested genre: ");
                    String reqGenre = scanner.nextLine();
                    System.out.println("All "+ reqGenre +" books available in the library: ");
                    freshLibrary.printGenre(reqGenre);
                }
                case 6 -> {
                    System.out.println("Please, enter the requested author: ");
                    String reqAuthor = scanner.nextLine();
                    System.out.println("Number of "+ reqAuthor +" books available in the library: ");
                    System.out.println(freshLibrary.getNumberOfBooksByAuthor(reqAuthor));
                }
                case 7 -> {
                    System.out.println("Number of books available in the library: ");
                    System.out.println(freshLibrary.getNumberOfBooks());
                }
                case 8 -> {
                    System.out.println("Thanks, Goodbye!");
                    run = false;
                    scanner.close();
                }
                default -> System.out.println("Invalid choice. Please try again.");

            }

        }
    
    }
}
