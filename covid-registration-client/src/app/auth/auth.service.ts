import { Injectable, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Subject } from 'rxjs';
import { User } from '../user/user.model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import * as moment from 'moment';
import { take } from 'rxjs/operators';
import { UserRequest } from '../userRequest/models/userRequest.model';


export const AUTH_TOKEN_KEY="auth-token";
export const AUTH_USER_DATA="user-data";

@Injectable()
export class AuthService {
  
  private loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(AuthService.tokenAvailable());

  get isLoggedIn() {
    return this.loggedIn.asObservable();
  } 
  private static tokenAvailable(): boolean {
    // localStorage.setItem('token', 'false');
    return sessionStorage.getItem('isLogged') == "true" ? true : false;
  }

  constructor(
    private router: Router,
    private _http : HttpClient
  ) {}

  getAccountInfo() {
    let desirableUser: User = new User();
    let desirableUserRequest: UserRequest = new UserRequest();
    let emailNpass: User = JSON.parse(sessionStorage.getItem('user-data'));
    // console.log(emailNpass);
    desirableUser.email = emailNpass.email;
    desirableUser.password = emailNpass.password;
    this._http
      .post<User>('http://localhost:8080/login', desirableUser)
      .subscribe((item) => {
        // this.userSubject$.next(item);
        // this.userForAccountInfo = item;
        sessionStorage.setItem("User", JSON.stringify(item));
        desirableUser.id = item.id;
        this._http
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
  
  public nextFunctionForLoggedInObserver(isLoggedIn: boolean){
    this.loggedIn.next(isLoggedIn);
  }

  login(user: User) : Observable<any> {
    if (user.username !== '' && user.password !== '' ) {

      sessionStorage.setItem(AUTH_TOKEN_KEY, user.email+"random_string");
      sessionStorage.setItem(AUTH_USER_DATA, JSON.stringify(user));      
      console.log(sessionStorage);
      
      this.router.navigate(['/home']);

      return this._http.post<any>("http://localhost:8080/login", user);
    }
  }

  logout() {
    this.loggedIn.next(false);
    sessionStorage.removeItem('isLogged');
    this.router.navigate(['/login']);

    sessionStorage.removeItem(AUTH_TOKEN_KEY);
    sessionStorage.removeItem(AUTH_USER_DATA);
    console.log("after logout")
    console.log( sessionStorage);
  }  
}
