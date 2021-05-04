import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import {User} from './user.model';
import { TaskInstance } from '../task/models/taskInstance.model';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class UserService {

  constructor(private http: HttpClient) {
  }

  private userUrl = 'http://localhost:8080/backend/users';

  public getUsers() {
    return this.http.get<User[]>(this.userUrl + "/all");
  }

  public getUser(userId) {
    return this.http.get<User>(this.userUrl + "/" + userId);
  }

  public deleteUser(user) {
    return this.http.delete(this.userUrl + "/" + user.id);
  }

  public createUser(user) {
    return this.http.post<User>(this.userUrl, user);
  }

  public getAllTasks(id) {
    return this.http.get<TaskInstance[]>(this.userUrl + "/" + id + "/tasks")
  }

  public getActiveTasks(id) {
    return this.http.get<TaskInstance[]>(this.userUrl + "/" + id + "/active-tasks")
  }

}
