import { Component, OnInit } from '@angular/core';
import {User} from '../domain/user';
import {UserService} from '../user.service';
import {Router} from '@angular/router';
import {RootConst} from '../util/rootConst';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private message: string;
  public rootConst: RootConst;
  public aux: boolean;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
    this.aux = true;
    this.rootConst = new RootConst();
  }

  loginB() {
    this.aux = true;
  }
  registerB() {
    this.aux = false;
  }
  login(username: string, password: string): void {
    username = username.trim();
    password = password.trim();

    this.userService.login({username, password} as User).subscribe(res => {
        if (res.username.length > 0)
        {
          localStorage.setItem("username", res.username);
          localStorage.setItem("role", res.role);
          this.router.navigateByUrl(this.rootConst.LOGIN_SUCCESS);
        }
      },
      err => {
        if (err.status == 403) {
          this.message = 'Username or password wrong. Please try again';
        }
        console.log(this.message);
      });
  }

  addUser(name: string, phone: string, usernameSignIn: string, email: string, passwordSignIn: string, passwordII: string): void{
    name = name.trim();
    phone = phone.trim();
    usernameSignIn = usernameSignIn.trim();
    email = email.trim();
    passwordSignIn = passwordSignIn.trim();
    passwordII = passwordII.trim();
    if(passwordSignIn != passwordII)
    {
      this.message = 'Password does not match!';
      return;
    }
    else {
      this.userService.addUser(name, phone, email, usernameSignIn, passwordSignIn).subscribe(
        res => {
          this.loginB();
        },
        err => {
          if (err.status == 403)
            this.message = 'Username or password wrong. Please try again';
          console.log(this.message);
        });
    }
  }
}
