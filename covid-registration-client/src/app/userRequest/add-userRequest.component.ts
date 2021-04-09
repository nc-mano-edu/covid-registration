import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {UserRequest} from "./models/userRequest.model";
import {UserRequestService} from "./userRequest.service"
import {UserService} from "../user/user.service";
import {User} from "../user/user.model";


@Component({
  selector: 'app-request',
  templateUrl: './add-userRequest.component.html',
  providers: [UserRequestService],
  styles: []
})
export class AddUserRequestComponent implements OnInit {

  request: UserRequest = new UserRequest();
  user: User;

  constructor(public activatedRoute: ActivatedRoute,
              private userService: UserService,
              private userRequestService: UserRequestService) {
  }

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(params => {
        const userId = params['userId'];
        this.userService.getUser(userId).subscribe(user => {
          this.user = user;
        }, err => {
          this.user = null;
        });
      }
    );
  };

}
