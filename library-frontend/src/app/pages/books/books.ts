import {Component, inject, OnInit} from '@angular/core';
import {ReactiveFormsModule, FormsModule} from '@angular/forms';
import {Book, BookService} from '../../services/book';

@Component({
  selector: 'app-books',
  imports: [ReactiveFormsModule, FormsModule],
  templateUrl: './books.html',
  styleUrl: './books.css',
})
export class Books implements OnInit{
  bookService = inject(BookService);
  ngOnInit() {
    this.bookService.getNumOfBooks().subscribe(n => this.totalBooks = n);
  }
  totalBooks = 0;
  displayedBooks: Book[] = [];

  filterType     = '';
  filterValue    = '';
  searched = false;
  showEditMenu = false;
  activeForm: 'add' | 'delete' | null = null;
  newBook = { isbn: 0, author: '', title: '', genre: '' };
  deleteIsbn = 0;


  //All books functionalities
 toggleAllBooks(){
   if(this.displayedBooks.length > 0){
     this.displayedBooks = [];
     this.searched = false;
   } else {
     this.bookService.getAllBooks().subscribe({
       next:(returnedBooks) => {
         this.displayedBooks = returnedBooks;
         this.searched = true;
       },
       error: () => {alert('Something went wrong in retrieving the library.')}
     });
   }

 }
 applyFilter(){
   if (this.filterType.toLowerCase() === "author"){
     this.searched = true;
      this.bookService.getAllAuthor(this.filterValue).subscribe(
        (books) =>{
          this.displayedBooks = books;
        });

   } else if (this.filterType.toLowerCase() === "genre"){
     this.searched = true;
     this.bookService.getAllGenre(this.filterValue).subscribe(
       (books) => {
       this.displayedBooks = books;
       });

   } else {return;}

 }
 clearFilter(){
   this.filterType    = '';
   this.filterValue   = '';
   this.displayedBooks = [];
   this.searched      = false;
 }

 toggleEditMenu(){
   this.showEditMenu = !this.showEditMenu;

 }

 submitAdd(){
   const { isbn, author, title, genre } = this.newBook;
   this.bookService.addBook(isbn, author, title, genre).subscribe(() => {
     this.bookService.getNumOfBooks().subscribe(n => this.totalBooks = n);
     this.closeForm();
   });
 }

 submitDelete(){
   this.bookService.deleteBook(this.deleteIsbn).subscribe(() => {
     this.displayedBooks = this.displayedBooks.filter(b => b.isbn !== this.deleteIsbn);
     this.bookService.getNumOfBooks().subscribe(n => this.totalBooks = n);
     this.closeForm();
   });
 }
 showForm(type: 'add' | 'delete'){
   this.activeForm = type;
   this.showEditMenu = false;

 }

 closeForm(){
   this.activeForm = null;
   this.newBook    = { isbn: 0, author: '', title: '', genre: '' };
   this.deleteIsbn = 0;
 }
}
