import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { LoginStorage } from '../../login/login.storage';

@Injectable()
export class RestService {

  private readonly URL = 'http://localhost:8080/api/';
  private headers;

  constructor(private http: Http, private loginStorage: LoginStorage) {
    this.headers = new Headers();
    this.headers.append('Content-Type', 'application/json');
    this.headers.append('token', this.loginStorage.getToken);
  }

  get(resource: string): Observable<any> {
    return this.http
      .get(`${this.URL}${resource}`, { headers: this.headers })
      .map(result => result.json())
      .catch(error => Observable.throw(error));
  }

  upload(resource: String, file: File): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('file', file);

    return this.http
      .post(`${this.URL}${resource}`, formData)
      .map(res => res.json())
      .catch(error => Observable.throw(error));
  }
}
