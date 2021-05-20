import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {UserRequest} from "../models/userRequest.model";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class UserRequestService {

  constructor(private http: HttpClient) {
  }

  private userRequestUrl = 'http://localhost:8080/backend/userRequest';

  public createRequest(request) {
    return this.http.post<UserRequest>(this.userRequestUrl, request);
  }

  public fillDoctorRecommendations(id, recommendations) {
    return this.http.post<UserRequest>(`${this.userRequestUrl}/${id}/recommendations`, recommendations);
  }

  public closeUserRequest(id, recommendations) {
    return this.http.post<UserRequest>(`${this.userRequestUrl}/${id}/close`, recommendations);
  }

}
