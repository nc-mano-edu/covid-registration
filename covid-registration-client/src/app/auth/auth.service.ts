import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Subject } from 'rxjs';
import { User } from '../user/user.model';

@Injectable()
export class AuthService {
  private loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(
    AuthService.tokenAvailable()
  );
  private onLogginPage: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(
    false
  );
  public userSubject$: BehaviorSubject<any> = new BehaviorSubject(new User());
  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }
  get isOnLogginPage() {
    return this.onLogginPage.asObservable();
  }
  private static tokenAvailable(): boolean {
    // localStorage.setItem('token', 'false');
    return localStorage.getItem('isLogged') == 'true' ? true : false;
  }

  constructor(private router: Router, private httpClient: HttpClient) {}

  getAccountInfo() {
    let desirableUser: User = new User();
    let emailNpass: User = JSON.parse(sessionStorage.getItem("user-data"));
    console.log(emailNpass);
    desirableUser.email = emailNpass.email;
    desirableUser.password = emailNpass.password;
    this.httpClient
      .post<any>(
        'http://localhost:8080/login',
        desirableUser
        )
      .subscribe(this.userSubject$);
      this.userSubject$.next(this.userSubject$.value);
  }

  login(/*user: User*/) {
    // if (user.username !== '' && user.password !== '' ) {
    this.loggedIn.next(true);
    this.onLogginPage.next(false);
    localStorage.setItem('isLogged', 'true');
    this.router.navigate(['/home']);
    // }
  }

  logout() {
    this.loggedIn.next(false);
    this.onLogginPage.next(true);
    localStorage.removeItem('isLogged');
    this.router.navigate(['/login']);
  }
}
