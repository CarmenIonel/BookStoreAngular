import { Injectable } from '@angular/core';
import {RootConst} from './util/rootConst';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Order} from './domain/order';
import {Observable} from 'rxjs/Observable';


@Injectable()
export class OrderService {

  private rootConst: RootConst= new RootConst();
  public message: string;
  private ADD = this.rootConst.ADD_ORDER;
  private ORDERS = this.rootConst.ALL_ORDERS;
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  httpOptionsAuthorize = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': 'Basic'})
  };

  constructor(private http: HttpClient) {this.message = ''; }

  addOrder(){
    return this.http.post(`${this.ADD}${localStorage.getItem("username")}`, this.httpOptions);
  }

  allOrders(): Observable<Order[]> {
    return this.http.post<Order[]>(`${this.ORDERS}${localStorage.getItem("username")}`, this.httpOptions);
  }
}
