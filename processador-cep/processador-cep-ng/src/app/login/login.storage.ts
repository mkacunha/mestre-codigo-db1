import { Injectable } from '@angular/core';

@Injectable()
export class LoginStorage {

  private readonly ACCESS_TOKEN = 'accessToken';

  constructor() { }

  public register(accessToken: string): void {
    sessionStorage.setItem(this.ACCESS_TOKEN, accessToken);
  }

  public clear(): void {
    sessionStorage.removeItem(this.ACCESS_TOKEN);
  }

  public isContainsToken(): boolean {
    let token: string = sessionStorage.getItem(this.ACCESS_TOKEN);
    return token && token != 'undefined' && token.trim().length > 0;
  }

  public getToken(): string {
    return sessionStorage.getItem(this.ACCESS_TOKEN);
  }

}
