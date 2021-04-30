import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder,  Validators, AbstractControl, ValidationErrors } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/user/user.model';
import { RegistrationService } from '../../services/registration.service';

class CustomValidators {
  static passwordsMatch (control: AbstractControl): ValidationErrors {
    const password = control.get('password').value;
    const confirmPassword = control.get('confirmPassword').value;

    if((password === confirmPassword) && (password !== null && confirmPassword !== null)) {
      return null;
    } else {
      return {passwordsNotMatching: true};
    }
  }
}

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  newRegistryForm : FormGroup;
  user = new User;

  constructor(
    private _router: Router,
    private _service: RegistrationService ,
    private formBuilder: FormBuilder,
  ) { }

  ngOnInit(): void {

    this.newRegistryForm = this.formBuilder.group({
      firstName: [null, [Validators.required]],
      middleName: [null, [Validators.required]],
      lastName: [null, [Validators.required]],
      dateOfBirth: [null, [Validators.required]],
      insuranceNumber: [null, [Validators.required]],
      phoneNumber: [null, [Validators.required]],      
      username: [null, [Validators.required]],
      email: [null, [
        Validators.required,
        Validators.email,
        Validators.minLength(6)
      ]],
      password: [null, [
        Validators.required,
        Validators.minLength(3)
      ]],
      confirmPassword: [null, [Validators.required]]
    },{
      validators: CustomValidators.passwordsMatch 
    })
     
  }

  onSubmit(){

    this._service.registerUser(this.newRegistryForm.value).subscribe(
      data => {
        console.log("response recieved");
        this._router.navigate(['/login'])
      },
      error => {
        console.log ("exception occured");       
      }
    )

  }

}
