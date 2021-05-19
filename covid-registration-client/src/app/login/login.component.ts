import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { AuthService } from '../auth/auth.service';
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  myLoginForm: FormGroup;
  private formSubmitAttempt: boolean;
  errorMsg = "";

  constructor(
    private _authService: AuthService,
    private _router : Router
  ) { }

  ngOnInit(): void {
    this.myLoginForm = new FormGroup({
      email: new FormControl(null, [Validators.required, Validators.email, Validators.minLength(6)]),
      password: new FormControl(null, [Validators.required, Validators.minLength(1)])
    })
  }

  isFieldInvalid(field: string) {
    return (
      (!this.myLoginForm.get(field).valid && this.myLoginForm.get(field).touched) ||
      (this.myLoginForm.get(field).untouched && this.formSubmitAttempt)
    );
  }

  onSubmit(){
    this._authService.login(this.myLoginForm.value).subscribe(
      data => {
        console.log("response recieved");
        this._authService.getAccountInfo();
        this._router.navigate(['/home'])
      } ,
      error =>  {
        this.errorMsg = "Invalid username and passwor. Please try again"
        console.log("exception occured");
        alert('Вы ввели неверный логин или пароль, попробуйте еще раз')
        this._router.navigate(['/login'])
      }
    )
    this.formSubmitAttempt = true;

  }

}
