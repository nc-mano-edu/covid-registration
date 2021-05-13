import { Injectable, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { User } from '../user/user.model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

export const AUTH_TOKEN_KEY="auth-token";
export const AUTH_USER_DATA="user-data";

@Injectable()
export class AuthService {
  
  private loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(AuthService.tokenAvailable());
  private onLogginPage: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  get isLoggedIn() {
    return this.loggedIn.asObservable();
  } 
  get isOnLogginPage(){
    return this.onLogginPage.asObservable();
  }
  private static tokenAvailable(): boolean {
    // localStorage.setItem('token', 'false');
    return localStorage.getItem('isLogged') == "true" ? true : false;
  }

  constructor(
    private router: Router,
    private _http : HttpClient
  ) {}

  login(user: User) : Observable<any> {
    if (user.username !== '' && user.password !== '' ) {
      this.loggedIn.next(true);
      this.onLogginPage.next(false);
      localStorage.setItem('isLogged', 'true');      

      sessionStorage.setItem(AUTH_TOKEN_KEY, user.email+"random_string");
      sessionStorage.setItem(AUTH_USER_DATA, JSON.stringify(user));
      console.log(sessionStorage);
      
      this.router.navigate(['/home']);

      return this._http.post<any>("http://localhost:8080/login", user);
    }
  }

  logout() {
    this.loggedIn.next(false);
    this.onLogginPage.next(true);
    localStorage.removeItem('isLogged');
    this.router.navigate(['/login']);

    sessionStorage.removeItem(AUTH_TOKEN_KEY);
    sessionStorage.removeItem(AUTH_USER_DATA);
    console.log("after logout")
    console.log( sessionStorage);
  }  


  /*
  private loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(AuthService.tokenAvailable());
  private onLogginPage: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  get isLoggedIn() {
    return this.loggedIn.asObservable();
  } 
  get isOnLogginPage(){
    return this.onLogginPage.asObservable();
  }
  private static tokenAvailable(): boolean {
    // localStorage.setItem('token', 'false');
    return localStorage.getItem('isLogged') == "true" ? true : false;
  }

  constructor(
    private router: Router
  ) {}

  login(user: User) {
    if (user.username !== '' && user.password !== '' ) {
      this.loggedIn.next(true);
      this.onLogginPage.next(false);
      localStorage.setItem('isLogged', 'true');
      this.router.navigate(['/home']);
    }
  }

  logout() {
    this.loggedIn.next(false);
    this.onLogginPage.next(true);
    localStorage.removeItem('isLogged');
    this.router.navigate(['/login']);
  }  
  */
}
