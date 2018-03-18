import { Component, OnInit } from '@angular/core';
import {BookService} from "../book.service";
import {Book} from "../domain/book";
import {User} from "../domain/user";
import {UserService} from "../user.service";
import {OrderService} from "../order.service";
import {Order} from "../domain/order";
import {ShoppingCartService} from "../shopping-cart.service";
import {ShoppingCartItem} from "../domain/shoppingCartItem";
import {ShoppingCart} from "../domain/shoppingCart";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  private message : string;
  private role :string;
  private admin: boolean;

  //admin
  private addBook: boolean;
  private deleteBook: boolean;
  private updateBook: boolean;

  //all
  private searchTitle: boolean;
  private searchAuthor: boolean;
  private searchGenre: boolean;
  private updateAccount: boolean;
  private viewAccount: boolean;
  private seeDetails: boolean;

  //user
  private viewBooks: boolean;
  private viewCart: boolean;
  private viewOrders:boolean;
  private detailAdd:boolean;
  private detailRemove:boolean;

  private list : Book[];
  private list2 : Order[];
  private bookAux : Book;
  private cart = new ShoppingCart();
  private listItem : ShoppingCartItem[];
  private userView = new User();

  constructor(private bookService : BookService,
              private userService : UserService,
              private orderService : OrderService,
              private shoppingCartService : ShoppingCartService) { }

  ngOnInit() {
    this.role = localStorage.getItem("role");
    if(this.role == "USER")
      this.admin = false;
    else
      this.admin = true;

  }

  logout():void{
    localStorage.clear();
    location.replace('');
  }

  addBookB(): void{
    this.addBook = true;
    this.deleteBook= false;
    this.updateBook= false;
    this.searchTitle= false;
    this.searchAuthor= false;
    this.searchGenre= false;
    this.updateAccount= false;
    this.viewAccount = false;
    this.seeDetails = false;
  }

  updateBookB(): void{
    this.addBook = false;
    this.deleteBook= false;
    this.updateBook= true;
    this.searchTitle= false;
    this.searchAuthor= false;
    this.searchGenre= false;
    this.updateAccount= false;
    this.viewAccount = false;
    this.seeDetails = false;
  }

  deleteBookB(): void{
    this.addBook = false;
    this.deleteBook= true;
    this.updateBook= false;
    this.searchTitle= false;
    this.searchAuthor= false;
    this.searchGenre= false;
    this.updateAccount= false;
    this.viewAccount = false;
    this.seeDetails = false;
  }

  updateAccountB(): void{
    this.addBook = false;
    this.deleteBook= false;
    this.updateBook= false;
    this.searchTitle= false;
    this.searchAuthor= false;
    this.searchGenre= false;
    this.updateAccount= true;
    this.viewAccount = false;
    this.seeDetails = false;
    this.viewBooks = false;
    this.viewOrders = false;
    this.viewCart = false;
    this.detailAdd = false;
    this.detailRemove = false;
  }

  viewAccountB(): void {
    this.addBook = false;
    this.deleteBook= false;
    this.updateBook= false;
    this.searchTitle= false;
    this.searchAuthor= false;
    this.searchGenre= false;
    this.updateAccount= false;
    this.viewAccount = true;
    this.seeDetails = false;
    this.viewBooks = false;
    this.viewOrders = false;
    this.viewCart = false;
    this.detailAdd = false;
    this.detailRemove = false;
  }

  searchTitleB(): void{
    this.addBook = false;
    this.deleteBook= false;
    this.updateBook= false;
    this.searchTitle= true;
    this.searchAuthor= false;
    this.searchGenre= false;
    this.updateAccount= false;
    this.viewAccount = false;
    this.seeDetails = false;
    this.viewBooks = false;
    this.viewOrders = false;
    this.viewCart = false;
    this.detailAdd = false;
    this.detailRemove = false;
  }

  searchAuthorB(): void{
    this.addBook = false;
    this.deleteBook= false;
    this.updateBook= false;
    this.searchTitle= false;
    this.searchAuthor= true;
    this.searchGenre= false;
    this.updateAccount= false;
    this.viewAccount = false;
    this.seeDetails = false;
    this.viewBooks = false;
    this.viewOrders = false;
    this.viewCart = false;
    this.detailAdd = false;
    this.detailRemove = false;
  }

  searchGenreB(): void {
    this.addBook = false;
    this.deleteBook= false;
    this.updateBook= false;
    this.searchTitle= false;
    this.searchAuthor= false;
    this.searchGenre= true;
    this.updateAccount= false;
    this.viewAccount = false;
    this.seeDetails = false;
    this.viewBooks = false;
    this.viewOrders = false;
    this.viewCart = false;
    this.detailAdd = false;
    this.detailRemove = false;
  }

  viewBooksB(): void{
    this.addBook = false;
    this.deleteBook= false;
    this.updateBook= false;
    this.searchTitle= false;
    this.searchAuthor= false;
    this.searchGenre= false;
    this.updateAccount= false;
    this.viewAccount = false;
    this.seeDetails = false;
    this.viewBooks = true;
    this.viewOrders = false;
    this.viewCart = false;
    this.detailAdd = false;
    this.detailRemove = false;

    this.bookService.allBooks().subscribe(
      books=>{this.list = books;}
    );
  }

  viewShoppingCartB(): void{
    this.addBook = false;
    this.deleteBook= false;
    this.updateBook= false;
    this.searchTitle= false;
    this.searchAuthor= false;
    this.searchGenre= false;
    this.updateAccount= false;
    this.viewAccount = false;
    this.seeDetails = false;
    this.viewBooks = false;
    this.viewOrders = false;
    this.viewCart = true;
    this.detailAdd = false;
    this.detailRemove = false;

    this.shoppingCartService.getCart().subscribe(
      shoppingCart=>{this.cart = shoppingCart;}
    );

    this.listItem = this.cart.items;
  }

  viewOrdersB(): void{
    this.addBook = false;
    this.deleteBook= false;
    this.updateBook= false;
    this.searchTitle= false;
    this.searchAuthor= false;
    this.searchGenre= false;
    this.updateAccount= false;
    this.viewAccount = false;
    this.seeDetails = false;
    this.viewBooks = false;
    this.viewOrders = true;
    this.viewCart = false;
    this.detailAdd = false;
    this.detailRemove = false;

    this.orderService.allOrders().subscribe(
      orders=>{this.list2 = orders;}
    );
  }

  addItemB(): void{
    this.addBook = false;
    this.deleteBook= false;
    this.updateBook= false;
    this.searchTitle= false;
    this.searchAuthor= false;
    this.searchGenre= false;
    this.updateAccount= false;
    this.viewAccount = false;
    this.seeDetails = false;
    this.viewBooks = false;
    this.viewOrders = false;
    this.viewCart = false;
    this.detailAdd = true;
    this.detailRemove = false;
  }

  removeItemB(): void{
    this.addBook = false;
    this.deleteBook= false;
    this.updateBook= false;
    this.searchTitle= false;
    this.searchAuthor= false;
    this.searchGenre= false;
    this.updateAccount= false;
    this.viewAccount = false;
    this.seeDetails = false;
    this.viewBooks = false;
    this.viewOrders = false;
    this.viewCart = false;
    this.detailAdd = false;
    this.detailRemove = true;
  }

  addBookF(ISBN: string, title: string, author: string, genre: string, description: string, price: number, stock: number){
    ISBN = ISBN.trim();
    title = title.trim();
    author = author.trim();
    genre = genre.trim();
    description = description.trim();

    this.bookService.addBook(ISBN, title, author, genre, description, price, stock).subscribe(
      res =>{
        this.addBook = false;
      },
      err =>{
        if (err.status == 403)
          this.message = 'add book';
        console.log(this.message);
      }
    );
  }

  updateBookF(searchISBNU: string, descriptionU: string, priceU: number, stockU: number){
    searchISBNU = searchISBNU.trim();
    descriptionU = descriptionU.trim();
    var book = new Book();
    book.description = descriptionU;
    book.price = priceU;
    book.stock = stockU;

    this.bookService.updateBook(searchISBNU, book).subscribe(
      res =>{
        this.updateBook = false;
      },
    err =>{
      if (err.status == 403)
        this.message = 'update book';
      console.log(this.message);
    }
    );
  }

  deleteBookF(searchISBND: string){
    searchISBND = searchISBND.trim();

    this.bookService.deleteBook(searchISBND).subscribe(
      res=>{
        this.deleteBook = false;
      },
      err =>{
        if (err.status == 403)
          this.message = 'delete book';
        console.log(this.message);
      }
    );
  }

  generateReportF(){
    this.bookService.generateReport().subscribe(
      res=>{

      },
      err =>{
        if (err.status == 403)
          this.message = 'generate report';
        console.log(this.message);
      }
    );
  }

  searchTitleF(title: string){
    title = title.trim();
    this.bookService.searchTitle(title).subscribe(
      books=>{this.list = books;}
    );
  }

  searchAuthorF(author: string){
    author = author.trim();
    this.bookService.searchAuthor(author).subscribe(
      books=>{this.list = books;}
    )
  }

  searchGenreF(genre: string){
    genre = genre.trim();
    this.bookService.searchGenre(genre).subscribe(
      books=>{this.list = books;}
    )
  }

  updateAccountF(searchUsername: string, nameUP: string, phoneUP: string , emailUP: string){
    searchUsername = searchUsername.trim();
    nameUP = nameUP.trim();
    phoneUP = phoneUP.trim();
    emailUP = emailUP.trim();
    var user = new User();
    user.name = nameUP;
    user.email = emailUP;
    user.phone = phoneUP;

    if( searchUsername != localStorage.getItem("username"))
    {
      return;
    }
    this.userService.updateUser(searchUsername, user).subscribe(
      res=>{
        this.updateAccount = false;
      },
      err=>{
        if (err.status == 403)
          this.message = 'update account';
        console.log(this.message);
      }
    );
  }

  viewAccountF(searchUsernameView: string){
    searchUsernameView = searchUsernameView.trim();

    if(searchUsernameView != localStorage.getItem("username"))
      return;
    this.seeDetails = true;
    this.userService.viewUser(searchUsernameView).subscribe(
      user=>{this.userView = user;}
    )
  }

  addItemF(ISBNADD: string, qantity: number){
    ISBNADD = ISBNADD.trim();
    this.bookService.getBook(ISBNADD).subscribe(
      book=>{this.bookAux = book;}
    );
    var item = new ShoppingCartItem();
    item.book = this.bookAux;
    item.booksOrdered = qantity;

    this.shoppingCartService.addItem(item).subscribe(
      res=>{
        this.detailAdd = false;
      },
      err =>{
        if (err.status == 403)
          this.message = 'add item';
        console.log(this.message);
      }
    );
  }

  removeItemF(ISBNDEL: string, qantityDEL: number){
    ISBNDEL = ISBNDEL.trim();
    this.bookService.getBook(ISBNDEL).subscribe(
      book=>{this.bookAux = book;}
    );
    var item = new ShoppingCartItem();
    item.book = this.bookAux;
    item.booksOrdered = qantityDEL;
    this.shoppingCartService.deleteItem(item).subscribe(
      res=>{
        this.detailRemove = false;
      },
      err =>{
        if (err.status == 403)
          this.message = 'remove item';
        console.log(this.message);
      }
    );
  }

  createOrderF(){
    this.orderService.addOrder().subscribe(
      res=>{

      },
      err =>{
        if (err.status == 403)
          this.message = 'create order';
        console.log(this.message);
      }
    );
  }
}
