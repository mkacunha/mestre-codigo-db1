import { Observable } from 'rxjs/Observable';
import { Http } from '@angular/http';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

import { LoginStorage } from './login.storage';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private storage: LoginStorage,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private http: Http) { }

  ngOnInit() {
    this.storage.clear();
  }

  teste() {
    console.log('okkkkkkkkkkk');
  }


  private login(): void {
    const wLogin = window
      .open('https://www.facebook.com/v2.12/dialog/oauth?client_id=791476447724146&redirect_uri=http://localhost:8080/login', '_blank');
    Observable.timer(5000).take(1).subscribe(() => {
      /*console.log(wLogin);
      console.log(wLogin.document);
      const pre = wLogin.document.getElementsByTagName('pre');
      if (pre) {
        console.log(JSON.parse(pre[0].innerText));
        wLogin.close();
      }*/
      wLogin.close();
    });
  }
}
