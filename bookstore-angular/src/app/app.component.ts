import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'My First Angular App';

  constructor() {
  }

  loginB(): void
  {
    location.replace('/login');
  }

  registerB(): void
  {
    location.replace('/register');
  }

}
