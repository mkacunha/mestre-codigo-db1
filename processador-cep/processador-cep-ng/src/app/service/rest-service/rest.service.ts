import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class RestService {

  private readonly URL = "http://localhost:8080/api/";
  private headers;

  constructor(private http: Http) {
    this.headers = new Headers();
    this.headers.append('Content-Type', 'application/json');
  }

  get(resource: string): Observable<any> {
    return this.http
      .get(`${this.URL}${resource}`, { headers: this.headers })
      .map(result => result.json())
      .catch(error => Observable.throw(error));
  }

  upload(resource: String, file: File): Observable<any> {
    let formData: FormData = new FormData();
    formData.append('file', file);

    return this.http
      .post(`${this.URL}${resource}`, formData)
      .map(res => res.json())
      .catch(error => Observable.throw(error));
  }
}
