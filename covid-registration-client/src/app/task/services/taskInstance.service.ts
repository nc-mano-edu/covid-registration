import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {TaskInstance} from "../models/taskInstance.model";

@Injectable()
export class TaskInstanceService {

  constructor(private http: HttpClient) {
  }

  private taskInstanceUrl = 'http://localhost:8080/backend/taskInstance';

  public findAll() {
    return this.http.get<TaskInstance[]>(this.taskInstanceUrl + '/all');
  }

  public find(id: string) {
    return this.http.get<TaskInstance>(this.taskInstanceUrl + '/' + id);
  }

  public findActive() {
    return this.http.get<TaskInstance[]>(this.taskInstanceUrl + '/active');
  }

  public update(id: string, body: TaskInstance) {
    return this.http.put<TaskInstance>(this.taskInstanceUrl + '/' + id, body);
  }

}
