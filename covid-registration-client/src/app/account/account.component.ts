import {Component, OnDestroy, OnInit} from '@angular/core';
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
import {Subscription} from 'rxjs';
import {EventService} from '../eventService/event.service';

@Component({
  selector: 'user-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css'],
  providers: [UserService, UserRequestService]
})
export class AccountComponent implements OnInit, OnDestroy {
  user: User;
  userRequest: UserRequest;
  isDoctor: boolean;
  messageReceived: any;
  private subscriptionName: Subscription; //important to create a subscription

  constructor(
    private httpClient: HttpClient,
    private authService: AuthService,
    private userService: UserService,
    private userRequestService: UserRequestService,
    private activatedRoute: ActivatedRoute,
    private dialog: MatDialog,
    private _eventService: EventService
  ) {
    // subscribe to sender component messages
    this.subscriptionName= this._eventService.getUpdate().subscribe(
        message => { //message contains the data sent from service
          this.ngOnInit();
        }
     );
  }

  dataFormat(): void {
    if (this.userRequest.startDate) {
      this.userRequest.startDate = moment(this.userRequest.startDate).format("DD.MM.YYYY")
    }
    if (this.userRequest.endDate) {
      this.userRequest.endDate = moment(this.userRequest.endDate).format("DD.MM.YYYY");
    }
  }

  hasProp(o, name) {
    return o !== null && o !== undefined && o.hasOwnProperty(name);
  }

  giveRecommendations(): void {
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
        }, error => {
          if (error.error.status === 500) {
            alert('The recommendation text must contain at least 3 characters!');
          }
        });
      }
    });
  }

  endTreatment(): void {
    const dialogRef = this.dialog.open(RecommendationDialog, {
      data: {
        message: 'Enter your recommendations if necessary',
        label: 'Recommendation',
        buttonText: {
          ok: 'Finish'
        }
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result != undefined) {
        this.userRequestService.closeUserRequest(this.userRequest.requestId, result).subscribe(request => {
          this.userRequest = request;
        });
      }
    });
  }


  ngOnDestroy() { // It's a good practice to unsubscribe to ensure no memory leaks
    this.subscriptionName.unsubscribe();
  }

  ngOnInit(): void {
    console.log("Account ngOnInit");
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
