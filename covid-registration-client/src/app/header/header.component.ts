import { Observable } from 'rxjs';
import { AuthService } from '../auth/auth.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isLoggedIn$: Observable<boolean>;
  
  isPatient: boolean;
  isDoctor: boolean;
  isAdmin: boolean;
  

  constructor(private authService: AuthService,
    private _router : Router
    ) { }

  ngOnInit() {
    this.isLoggedIn$ = this.authService.isLoggedIn;
    this.isPatient = (sessionStorage.getItem("isPatient")=="true");
    this.isDoctor = (sessionStorage.getItem("isDoctor")=="true");
    this.isAdmin = (sessionStorage.getItem("isAdmin")=="true");
  }

  onLogout() {
    this.authService.logout();
  }

  getAccountInfo(){
    this.authService.getAccountInfo();
  }

  getAllTaskInstances(){
    this._router.navigate(['/task_instances'], {      
    })
  }

  getActiveTaskInstances(){
    this._router.navigate(['/task_instances/active'], {      
    })
  }  

}
