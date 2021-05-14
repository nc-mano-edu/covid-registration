import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {UserRequest} from "./models/userRequest.model";
import {UserRequestService} from "./services/userRequest.service"
import {UserService} from "../user/user.service";
import {User} from "../user/user.model";
import {SymptomService} from "./services/symptom.service";
import {Symptom} from "./models/symptom.model";
import {MatChip} from "@angular/material/chips";


@Component({
  selector: 'app-request',
  templateUrl: './add-userRequest.component.html',
  providers: [UserRequestService, SymptomService],
  styleUrls: ['./userRequest.component.css']
})
export class AddUserRequestComponent implements OnInit {

  request: UserRequest = new UserRequest();
  user: User;
  symptoms: Symptom[];
  symptomsSelected = new Set();

  constructor(public activatedRoute: ActivatedRoute,
              private userService: UserService,
              private symptomService: SymptomService,
              private userRequestService: UserRequestService) {
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
    request.user = this.user;
    request.symptoms = this.symptoms.filter(symptom =>
      this.symptomsSelected.has(symptom.symptomId)
    );
    this.userRequestService.createRequest(request).subscribe(data => {
      alert("UserRequest created successfully.");
    });
  }

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(params => {
        const userId = params['userId'];

        this.userService.getUser(userId).subscribe(user => {
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
