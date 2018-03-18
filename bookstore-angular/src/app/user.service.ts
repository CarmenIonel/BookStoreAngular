import { Injectable } from '@angular/core';
import {RootConst} from './util/rootConst';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from './domain/user';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class UserService {

  private rootConst: RootConst= new RootConst();
  public message: string;
  private UPDATE = this.rootConst.UPDATE_USER;
  private DELETE = this.rootConst.DELETE_USER;
  private VIEW = this.rootConst.VIEW_USER;
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  httpOptionsAuthorize = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': 'Basic'})
  };

  constructor(private http: HttpClient) {this.message = ''; }

  login(user: User): Observable<User> {
    let body = JSON.stringify({username: user.username, password: user.password});
    return this.http.post<User>(this.rootConst.SERVER_LOGIN, body, this.httpOptionsAuthorize);

  }

  addUser(name: string, phone: string, email: string, usernameSignIn: string, passwordSignIn: string) {
    let body = JSON.stringify({name: name, phone: phone, email: email,
      username: usernameSignIn, password: passwordSignIn});
    return this.http.post<User>(this.rootConst.ADD_USER, body, this.httpOptions);

  }

  updateUser(username :string, user: User): any {
    let body = JSON.stringify({name: user.name, phone: user.phone, email: user.email, username: username});
    return this.http.post<User>(`${this.UPDATE}${username}`, body, this.httpOptions);
  }

  deleteUser(username: string): any {
    return this.http.post(`${this.DELETE}${username}`,username, this.httpOptions);
  }

  viewUser(username: string): Observable<User> {
    return this.http.post<User>(`${this.VIEW}${username}`,username, this.httpOptions);
  }
}
