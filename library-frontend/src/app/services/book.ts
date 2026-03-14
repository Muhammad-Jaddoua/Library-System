// services/book.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { forkJoin } from 'rxjs';

export interface Book {
  isbn: number;
  author: string;
  title: string;
  genre: string;
}

@Injectable({ providedIn: 'root' })
export class BookService {
  private apiUrl = '/api/books';

  constructor(private http: HttpClient) {}

  getAllBooks() {
    return this.http.get<Book[]>(this.apiUrl);
  }

  getBook(isbn: number) {
    return this.http.get<Book>(`${this.apiUrl}/${isbn}`);
  }

  addBook(isbn: number, author: string, title: string, genre: string) {
    return this.http.post(this.apiUrl, null, {
      params: { ISBN: isbn, author, title, genre }
    });
  }

  addBookAuto() {
    return forkJoin([
      this.addBook(200, "Ali", "Java", "Programming"),
      this.addBook(1010, "Borat", "USA", "Documentary"),
      this.addBook(1112, "Hitler", "Jewnation", "Cooking"),
      this.addBook(2100, "ramsey", "donuts", "slavery"),
    ]);
  }

  deleteBook(isbn: number) {
    return this.http.delete(`${this.apiUrl}/${isbn}`);
  }

  getNumOfBooksByAuthor(author: string){
    return this.http.get<number>(`${this.apiUrl}/count-author/${author}`)
  }

  getAllAuthor(author: string){
    return this.http.get<Book[]>(`${this.apiUrl}/filter-author/${author}`)

  }

  getAllGenre(genre: string){
    return this.http.get<Book[]>(`${this.apiUrl}/filter-genre/${genre}`)

  }

  getNumOfBooks(){
    return this.http.get<number>(`${this.apiUrl}/count`)
  }
}
