import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import * as moment from 'moment';
import { Observable } from 'rxjs';
import { take } from 'rxjs/operators';
import { AuthService } from '../auth/auth.service';
import { User } from '../user/user.model';
import { UserRequest } from '../userRequest/models/userRequest.model';

@Component({
  selector: 'user-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  user: User;
  userRequest: UserRequest;

  constructor(
    private httpClient: HttpClient,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.user = JSON.parse(sessionStorage.getItem("User"));
    this.userRequest = JSON.parse(sessionStorage.getItem("Request"))[0];

    let date: Date = new Date(this.userRequest.startDate);
    let tempVarForDateFormat = moment(date).format("DD.MM.YYYY");
    this.userRequest.startDate = tempVarForDateFormat.toString();
    tempVarForDateFormat = moment(this.userRequest.endDate).format("DD.MM.YYYY");
    this.userRequest.endDate = tempVarForDateFormat;

  }

}