import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from '../auth/auth.service';
import { User } from '../user/user.model';

@Component({
  selector: 'user-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css'],
})
export class AccountComponent implements OnInit {
  user: User;
  valuesOfUserObjectKeys: string[];
  constructor(
    private httpClient: HttpClient,
    private authService: AuthService
  ) {}

  ngOnInit() {
    // this.user$ = this.httpClient.get<any>(
    //   'http://localhost:8080/login',
    //   JSON.parse(sessionStorage.getItem('user-info'))
    // );
    // this.user$.subscribe(item=>this.user = item);
    // console.log(this.user);
    this.authService.userSubject$.subscribe((item) => {
      this.user = item;
      this.valuesOfUserObjectKeys = Object.values(this.user);
    });
  }
}
