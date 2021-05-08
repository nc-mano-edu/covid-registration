import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import * as moment from 'moment';
import { BehaviorSubject, Subject } from 'rxjs';
import { take } from 'rxjs/operators';
import { User } from '../user/user.model';
import { UserRequest } from '../userRequest/models/userRequest.model';

@Injectable()
export class AuthService {
  private loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(
    AuthService.tokenAvailable()
  );
  private onLogginPage: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(
    false
  );
  // public userSubject$: Subject<User> = new Subject();
  // public userRequestSubject$: Subject<UserRequest> = new Subject();
  // public userForAccountInfo: User;
  // public userRequestForAccountInfo: UserRequest;
  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }
  get isOnLogginPage() {
    return this.onLogginPage.asObservable();
  }
  private static tokenAvailable(): boolean {
    // localStorage.setItem('token', 'false');
    return sessionStorage.getItem('isLogged') == 'true' ? true : false;
  }

  constructor(private router: Router, private httpClient: HttpClient) {}

  getAccountInfo() {
    let desirableUser: User = new User();
    let desirableUserRequest: UserRequest = new UserRequest();
    let emailNpass: User = JSON.parse(sessionStorage.getItem('user-data'));
    // console.log(emailNpass);
    desirableUser.email = emailNpass.email;
    desirableUser.password = emailNpass.password;
    this.httpClient
      .post<User>('http://localhost:8080/login', desirableUser)
      .subscribe((item) => {
        // this.userSubject$.next(item);
        // this.userForAccountInfo = item;
        sessionStorage.setItem("User", JSON.stringify(item));
        desirableUser.id = item.id;
        this.httpClient
        .get<UserRequest>(
          'http://localhost:8080/backend/userRequest' +
            '?userId=' +
            desirableUser.id
        )
        .subscribe((item) => {
          // this.userRequestSubject$.next(item);
          // this.userRequestForAccountInfo = item;
          sessionStorage.setItem("Request", JSON.stringify(item));
        });
    
      });
  }

  login(/*user: User*/) {
    // if (user.username !== '' && user.password !== '' ) {
    this.loggedIn.next(true);
    this.onLogginPage.next(false);
    sessionStorage.setItem('isLogged', 'true');
    this.router.navigate(['/home']);
    // }
  }

  logout() {
    this.loggedIn.next(false);
    this.onLogginPage.next(true);
    sessionStorage.removeItem('isLogged');
    this.router.navigate(['/login']);
  }
}
