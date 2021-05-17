import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { Role } from "./role.model";
import { User } from './user.model';
import { UserService } from './user.service';

@Component({
  templateUrl: './add-user.component.html',
  styles:['.col-md-6{padding-left:10px; height:50%}']
})
export class AddUserComponent {

  user: User = new User();

  constructor(private router: Router, private userService: UserService) {

  }

  createUser(): void {
    this.userService.createUser(this.user)
      .subscribe( data => {
        alert("User created successfully.");
      });

  };

}
