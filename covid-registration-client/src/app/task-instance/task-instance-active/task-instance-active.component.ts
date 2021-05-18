import { Component, OnInit } from '@angular/core';
import { TaskInstance } from 'src/app/task/models/taskInstance.model';
import { TaskInstanceService } from 'src/app/task/services/taskInstance.service';

@Component({
  templateUrl: './task-instance-active.component.html',
  styleUrls: ['../../user/user.component.css'
             , '../../panel-admin/panel-admin.component.css']
})
export class TaskInstanceActiveComponent implements OnInit {

  taskInstances: TaskInstance[];

  constructor(private taskInstanceService: TaskInstanceService) { }

  ngOnInit(): void {
    this.taskInstanceService.findActive().subscribe(
      (data: TaskInstance[]) => {
        this.taskInstances = data;
      });
    this.taskInstances = null;
  }

}
