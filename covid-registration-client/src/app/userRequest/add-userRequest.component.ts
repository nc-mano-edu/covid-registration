import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {UserRequest} from "./models/userRequest.model";
import {UserRequestService} from "./services/userRequest.service"
import {UserService} from "../user/user.service";
import {User} from "../user/user.model";
import {SymptomService} from "./services/symptom.service";
import {Symptom} from "./models/symptom.model";
import {MatChip} from "@angular/material/chips";
import { AuthService } from '../auth/auth.service';


@Component({
  selector: 'app-request',
  templateUrl: './add-userRequest.component.html',
  providers: [UserRequestService, SymptomService],
  styleUrls: ['./userRequest.component.css']
})
export class AddUserRequestComponent implements OnInit {

  request: UserRequest = new UserRequest();
  user: User;
  userId: string;
  symptoms: Symptom[];
  symptomsSelected = new Set();

  constructor(public activatedRoute: ActivatedRoute,
              private router: Router,
              private userService: UserService,
              private symptomService: SymptomService,
              private userRequestService: UserRequestService,
              private authService:AuthService) {
  }

  symptomSelection(chip: MatChip) {
    const id = chip.value;
    if (this.symptomsSelected.has(id))
      this.symptomsSelected.delete(id);
    else
      this.symptomsSelected.add(id);
    chip.toggleSelected();
  }

  onSave() {
    const request = new UserRequest();
    request.startDate = new Date().toISOString().split('T')[0];
    request.treatmentState = 'STARTED';
    request.user = this.user;
    request.symptoms = this.symptoms.filter(symptom =>
      this.symptomsSelected.has(symptom.symptomId)
    );
    this.userRequestService.createRequest(request).subscribe(data => {
      this.router.navigate(['user/' + this.userId + '/tasks/active']);
      console.log(this.userId);
    });
    sessionStorage.setItem('Request', JSON.stringify(request));
    this.authService.getAccountInfo();
  }

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(params => {
        this.userId = params['userId'];

        this.userService.getUser(this.userId).subscribe(user => {
          this.user = user;
        }, err => {
          this.user = null;
        });

        this.symptomService.getAllSymptoms().subscribe(symptoms => {
          this.symptoms = symptoms;
        });
      }
    );
  };

}
