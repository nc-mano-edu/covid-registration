import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { User } from '../user/user.model';

@Injectable()
export class AuthService {
  private loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(AuthService.tokenAvailable());

  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }

  private static tokenAvailable(): boolean {
    return !!localStorage.getItem('token');
  }

  constructor(
    private router: Router
  ) {}

  login(user: User) {
    if (user.username !== '' && user.password !== '' ) {
      this.loggedIn.next(true);
      localStorage.setItem('token', 'true');
      this.router.navigate(['/home']);
    }
  }

  logout() {
    this.loggedIn.next(false);
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }
}
