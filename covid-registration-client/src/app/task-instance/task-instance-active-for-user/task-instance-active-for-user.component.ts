import { User } from 'src/app/user/user.model';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { TaskInstance } from 'src/app/task/models/taskInstance.model';
import { UserService } from 'src/app/user/user.service';

@Component({
  templateUrl: './task-instance-active-for-user.component.html',
  styleUrls: ['../../user/user.component.css'
             , '../../panel-admin/panel-admin.component.css']
})
export class TaskInstanceActiveForUserComponent implements OnInit {

  taskInstances: TaskInstance[];
  user: User;
  id: number;

  constructor(activatedRouter: ActivatedRoute, private userService: UserService) {
    this.id = activatedRouter.snapshot.params.id;
  }

  ngOnInit(): void {
    this.userService.getActiveTasks(this.id).subscribe(
      data => {
        this.taskInstances = data;
      }
    );
    this.userService.getUser(this.id).subscribe(
      data => {
        this.user = data;
      }
    );
  }

}
