import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/user/user.model';

export const AUTH_TOKEN_KEY="auth-token";
export const AUTH_USER_DATA="user-data";

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private _http : HttpClient) { }

  public registerUser(user : User) : Observable<any> {
    return this._http.post<any>("http://localhost:8080/registeruser", user);
  }

  public loginUser(user : User): Observable<any> {
    if(user.email !== '' && user.password!=='') {
      sessionStorage.setItem(AUTH_TOKEN_KEY, user.email+"random_string");
      sessionStorage.setItem(AUTH_USER_DATA, JSON.stringify(user));
      console.log(sessionStorage);
      return this._http.post<any>("http://localhost:8080/login", user);
    }
  }
}
