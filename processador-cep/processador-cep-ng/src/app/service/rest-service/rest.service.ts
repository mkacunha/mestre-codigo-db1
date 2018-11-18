import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { LoginStorage } from '../../login/login.storage';

@Injectable()
export class RestService {

  private readonly URL = 'http://localhost:8080/api/';

  constructor(private http: Http, private loginStorage: LoginStorage) {

  }

  get(resource: string): Observable<any> {
    return this.http
      .get(`${this.URL}${resource}`, { headers: this.headers })
      .map(result => result.json())
      .catch(error => Observable.throw(error));
  }

  upload(resource: String, session: string, file: File): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('file', file);

    const headers = new Headers();
    headers.append('session', session);

    return this.http
      .post(`${this.URL}${resource}`, formData, { headers: headers })
      .map(res => res.json())
      .catch(error => Observable.throw(error));
  }

  get headers() {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');

    if (this.loginStorage.isContainsToken) {
      const token = this.loginStorage.getToken;
      headers.append('token', token.toString());
    }

    return headers;
  }
}
