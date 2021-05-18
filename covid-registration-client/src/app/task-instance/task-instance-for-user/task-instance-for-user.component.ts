import { UserService } from '../../user/user.service';
import { ActivatedRoute } from '@angular/router';
import { User } from 'src/app/user/user.model';
import { TaskInstance } from '../../task/models/taskInstance.model';
import { Component, OnInit } from '@angular/core';

@Component({
  templateUrl: './task-instance-for-user.component.html',
  styleUrls: ['../../user/user.component.css'
             , '../../panel-admin/panel-admin.component.css']
})
export class TaskInstanceForUserComponent implements OnInit {

  taskInstances: TaskInstance[];
  id: number;
  user: User;

  constructor(activatedRouted: ActivatedRoute, private userService: UserService) {
    this.id = activatedRouted.snapshot.params.id;
  }

  ngOnInit(): void {
    this.userService.getAllTasks(this.id).subscribe(
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
