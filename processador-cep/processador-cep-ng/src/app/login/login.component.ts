import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

import { LoginStorage } from './login.storage';

declare const FB: any;

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private storage: LoginStorage, private router: Router) { }

  ngOnInit() {
    this.initFacebookLogin();
    this.storage.clear();
  }

  private initFacebookLogin(): void {
    FB.init({
      appId: '791476447724146',
      cookie: true,
      xfbml: true,
      version: 'v2.10'
    });
    FB.AppEvents.logPageView();
  }

  private login(): void {
    FB.login((result: any) => {
      this.storage.register(result.authResponse.accessToken);
      this.router.navigate(['/processador']);
    });
  }

}
