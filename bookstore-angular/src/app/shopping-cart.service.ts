import { Injectable } from '@angular/core';
import {RootConst} from './util/rootConst';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ShoppingCartItem} from './domain/shoppingCartItem';
import {Observable} from "rxjs/Observable";
import {ShoppingCart} from "./domain/shoppingCart";

@Injectable()
export class ShoppingCartService {
  private rootConst: RootConst= new RootConst();
  public message: string;
  private ADD = this.rootConst.ADD_ITEM;
  private DELETE = this.rootConst.DELETE_ITEM;
  private GET = this.rootConst.GET_CART;

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  httpOptionsAuthorize = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': 'Basic'})
  };

  constructor(private http: HttpClient) {this.message = ''; }

  addItem(item: ShoppingCartItem){
    let body = JSON.stringify({booksOrdered: item.booksOrdered, book: item.book});
    return this.http.post(`${this.ADD}${localStorage.getItem("username")}`, body, this.httpOptions);
  }

  deleteItem(item: ShoppingCartItem){
    let body = JSON.stringify({booksOrdered: item.booksOrdered, book: item.book});
    return this.http.post(`${this.DELETE}${localStorage.getItem("username")}`, body, this.httpOptions);
  }

  getCart(): Observable<ShoppingCart>{
    return this.http.post<ShoppingCart>(`${this.GET}${localStorage.getItem("username")}`, this.httpOptions);
  }
}
