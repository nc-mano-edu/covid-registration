import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import * as moment from 'moment';
import {AuthService} from '../auth/auth.service';
import {User} from '../user/user.model';
import {UserRequest} from '../userRequest/models/userRequest.model';
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../user/user.service";
import {MatDialog} from "@angular/material/dialog";
import {RecommendationDialog} from "./dialog/rec-dialog.component";
import {UserRequestService} from "../userRequest/services/userRequest.service";

@Component({
  selector: 'user-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css'],
  providers: [UserService, UserRequestService]
})
export class AccountComponent implements OnInit {
  user: User;
  userRequest: UserRequest;
  isDoctor: boolean;

  constructor(
    private httpClient: HttpClient,
    private authService: AuthService,
    private userService: UserService,
    private userRequestService: UserRequestService,
    private activatedRoute: ActivatedRoute,
    private dialog: MatDialog
  ) {
  }

  dataFormat(): void {
    if (this.userRequest.startDate) {
      this.userRequest.startDate = moment(this.userRequest.startDate).format("DD.MM.YYYY")
    }
    if (this.userRequest.endDate) {
      this.userRequest.endDate = moment(this.userRequest.endDate).format("DD.MM.YYYY");
    }
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(RecommendationDialog, {
      data: {
        message: 'Enter your recommendations',
        label: 'Recommendation'
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.userRequestService.fillDoctorRecommendations(this.userRequest.requestId, result).subscribe(request => {
          this.userRequest = request;
        });
      }
    });
  }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      const userId = params['userId'];
      this.isDoctor = (sessionStorage.getItem("isDoctor") == "true");

      if (userId) {
        this.userService.getUser(userId).subscribe(user => {
          this.user = user;
        }, err => {
          this.user = null;
        });
        this.httpClient
          .get<UserRequest[]>('http://localhost:8080/backend/userRequest?userId=' + userId)
          .subscribe((item) => {
            this.userRequest = item[0];
            this.dataFormat();
          });
      } else {
        this.isDoctor = false;
        this.user = JSON.parse(sessionStorage.getItem("User"));
        this.userRequest = JSON.parse(sessionStorage.getItem("Request"))[0];
        this.dataFormat();
      }

    });
  }

}
