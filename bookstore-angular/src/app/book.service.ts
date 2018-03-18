import { Injectable } from '@angular/core';
import {RootConst} from './util/rootConst';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Book} from './domain/book';
import {Observable} from "rxjs/Observable";

@Injectable()
export class BookService {

  private rootConst: RootConst= new RootConst();
  public message: string;
  private DELETE = this.rootConst.DELETE_BOOK;
  private UPDATE = this.rootConst.UPDATE_BOOK;
  private TITLE = this.rootConst.SEARCH_TITLE;
  private AUTHOR = this.rootConst.SEARCH_AUTHOR;
  private GENRE = this.rootConst.SEARCH_GENRE;
  private GET = this.rootConst.GET_BOOK;
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  httpOptionsAuthorize = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': 'Basic'})
  };

  constructor(private http: HttpClient) {this.message = ''; }

  addBook(ISBN: string, title: string, author: string, genre: string, description: string, price: number, stock: number) {
    let body = JSON.stringify({title: title, author: author, genre: genre,
      description: description, price: price, stock: stock, isbn: ISBN});
    return this.http.post<Book>(this.rootConst.ADD_BOOK, body, this.httpOptions);
  }

  deleteBook(ISBN: string) {
    return this.http.post(`${this.DELETE}${ISBN}`, this.httpOptions);
  }

  updateBook(ISBN: string, book: Book): Observable<Book> {
    let body = JSON.stringify({description: book.description, price: book.price, stock: book.stock});
    return this.http.post<Book>(`${this.UPDATE}${ISBN}`, body, this.httpOptions);
  }

  generateReport() {
    return this.http.post(this.rootConst.GENERATE_REPORT, this.httpOptions);
  }

  searchTitle(title: string): Observable<Book[]> {
    return this.http.post<Book[]>(`${this.TITLE}${title}`, this.httpOptions);
  }

  searchAuthor(author: string): Observable<Book[]> {
    return this.http.post<Book[]>(`${this.AUTHOR}${author}`, this.httpOptions);
  }

  searchGenre(genre: string): Observable<Book[]> {
    return this.http.post<Book[]>(`${this.GENRE}${genre}`, this.httpOptions);
  }

  allBooks(): Observable<Book[]>{
    return this.http.post<Book[]>( this.rootConst.ALL_BOOKS, this.httpOptions);
  }

  getBook(ISBN: string) : Observable<Book>{
    return this.http.post<Book>(`${this.GET}${ISBN}`, this.httpOptions);
  }
}
