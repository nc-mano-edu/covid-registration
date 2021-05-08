import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/auth.service';
import { RegistrationService } from '../../services/registration.service';

@Component({
  selector: 'app-new-login',
  templateUrl: './new-login.component.html',
  styleUrls: ['./new-login.component.css']
})
export class NewLoginComponent implements OnInit {

  myLoginForm: FormGroup; 
  private formSubmitAttempt: boolean;
  errorMsg = "";

  constructor(
    private _service: RegistrationService,
    private _router : Router,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.myLoginForm = new FormGroup({
      email: new FormControl(null, [Validators.required, Validators.email, Validators.minLength(6)]),
      password: new FormControl(null, [Validators.required, Validators.minLength(3)])
    })
  }

  isFieldInvalid(field: string) {
    return (
      (!this.myLoginForm.get(field).valid && this.myLoginForm.get(field).touched) ||
      (this.myLoginForm.get(field).untouched && this.formSubmitAttempt)
    );
  }
  
  onSubmit(){
    this._service.loginUser(this.myLoginForm.value).subscribe(
      data => {
        console.log("response recieved");
        this.authService.login()
        this.authService.getAccountInfo();
        this._router.navigate(['/home'])

      } ,
      error =>  {
        this.errorMsg = "Invalid username and password. Please try again"
        console.log("exception occured");
      }
    )
    this.formSubmitAttempt = true;
  }

}
