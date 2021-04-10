import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Symptom} from "../models/symptom.model";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class SymptomService {

  constructor(private http: HttpClient) {
  }

  private symptomUrl = 'http://localhost:8080/backend/symptom';

  public getAllSymptoms() {
    return this.http.get<Symptom[]>(this.symptomUrl + '/all');
  }

}
