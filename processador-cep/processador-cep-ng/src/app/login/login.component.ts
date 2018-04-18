import { Http } from '@angular/http';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

import { LoginStorage } from './login.storage';

declare const FB: any;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private storage: LoginStorage, private router: Router, private http: Http) { }

  ngOnInit() {
    this.storage.clear();
  }


  private login(): void {
    console.log('https://www.facebook.com/v2.12/dialog/oauth?client_id=791476447724146&redirect_uri=http://localhost:8080/login');
    console.log(FB);
    this.http
      .get('https://www.facebook.com/v2.12/dialog/oauth?client_id=791476447724146&redirect_uri=http://localhost:8080/login')
      .subscribe(res => console.log(res));
  }

}
