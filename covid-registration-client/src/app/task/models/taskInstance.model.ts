import {Task} from "./task.model";
import {TaskInstanceData} from "./taskInstanceData.model";
import { UserRequest } from '../../userRequest/models/userRequest.model';

export class TaskInstance {

  id: string;
  task: Task;
  request: UserRequest;
  createdTime: string;
  finishedTime: string;
  active: string;
  data: TaskInstanceData[];

}
