import { TaskInstanceService } from '../../task/services/taskInstance.service';
import { TaskInstance } from '../../task/models/taskInstance.model';
import { Component, OnInit } from '@angular/core';

@Component({
  templateUrl: './task-instance.component.html',
  styleUrls: ['../../user/user.component.css'
               , '../../panel-admin/panel-admin.component.css']
})
export class TaskInstanceComponent implements OnInit {

  taskInstances: TaskInstance[];

  constructor(private taskInstanceService: TaskInstanceService) { }

  ngOnInit(): void {
    this.taskInstanceService.findAll().subscribe(
      (data: TaskInstance[]) => {
        this.taskInstances = data;
      });
    this.taskInstances = null;
  }

}
