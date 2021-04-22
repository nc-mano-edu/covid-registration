import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {TaskInstanceService} from "./services/taskInstance.service";
import {TaskInstance} from "./models/taskInstance.model";

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  providers: [TaskInstanceService],
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  task: TaskInstance;

  constructor(
    private activatedRoute: ActivatedRoute,
    private taskInstanceService: TaskInstanceService) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      const id: string = params['task_id'];
      this.taskInstanceService.find(id).subscribe(data => {
        this.task = data;
        console.log(this.task);
      });
    });
  }

}
