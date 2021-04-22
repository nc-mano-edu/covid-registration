import {Task} from "./task.model";
import {TaskInstanceData} from "./taskInstanceData.model";

export class TaskInstance {

  id: string;
  task: Task;
  createdTime: string;
  finishedTime: string;
  isActive: string;
  data: TaskInstanceData[];

}
