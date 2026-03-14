import {Component, inject, OnInit, signal} from '@angular/core';
import {ReactiveFormsModule, FormsModule} from '@angular/forms';
import {Book, BookService} from '../../services/book';
import {Router} from '@angular/router';
import {AuthService} from '../../services/auth';


@Component({
  selector: 'app-books',
  imports: [ReactiveFormsModule, FormsModule],
  templateUrl: './books.html',
  styleUrl: './books.css',
})
export class Books implements OnInit{
  bookService = inject(BookService);
  authService = inject(AuthService);
  router = inject(Router);

  ngOnInit() {
    this.bookService.getNumOfBooks().subscribe(n => this.totalBooks = n);
  }
  totalBooks = 0;
  displayedBooks = signal<Book[]>([]);

  filterType   = '';
  filterValue  = '';
  searched     = false;
  isShowingAll = false;
  showEditMenu = false;
  activeForm: 'add' | 'delete' | null = null;
  newBook = { isbn: 0, author: '', title: '', genre: '' };
  deleteIsbn = 0;

  showAllBooks() {
    this.filterType  = '';
    this.filterValue = '';
    this.bookService.getAllBooks().subscribe({
      next: (returnedBooks) => {
        this.displayedBooks.set(returnedBooks);
        this.searched       = true;
        this.isShowingAll   = true;
      },
      error: () => { alert('Something went wrong in retrieving the library.'); }
    });
  }

  applyFilter() {
    if (!this.filterType || !this.filterValue.trim()) return;
    this.isShowingAll = false;

    if (this.filterType.toLowerCase() === 'author') {
      this.searched = true;
      this.bookService.getAllAuthor(this.filterValue).subscribe(books => {
        this.displayedBooks.set(books);
      });
    } else if (this.filterType.toLowerCase() === 'genre') {
      this.searched = true;
      this.bookService.getAllGenre(this.filterValue).subscribe(books => {
        this.displayedBooks.set(books);
      });
    }
  }

  clearFilter() {
    this.filterType     = '';
    this.filterValue    = '';
    this.displayedBooks.set([]);
    this.searched       = false;
    this.isShowingAll   = false;
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
     this.displayedBooks.update(books => books.filter(b => b.isbn !== this.deleteIsbn));
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

 generateIsbn() {
   const n1 = Math.floor(Math.random() * 9) + 1; // 1–9, avoid leading zero
   const n2 = Math.floor(Math.random() * 10);
   const n3 = Math.floor(Math.random() * 10);
   const n4 = (n1 * 3 + n2 * 2 + n3 * 1) % 4;
   this.newBook.isbn = parseInt(`${n1}${n2}${n3}${n4}`, 10);
 }

 logout(){
    this.authService.logout();
    this.router.navigate(['login'])
 }
}
