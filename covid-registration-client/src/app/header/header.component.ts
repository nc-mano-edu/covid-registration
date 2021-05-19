import { Observable } from 'rxjs';
import { AuthService } from '../auth/auth.service';
import { Component, OnInit } from '@angular/core';
import {Subscription} from 'rxjs';
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
  private querySubscription: Subscription;
  userIdFromStorage:string;

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

  getAllTasks() {    
    this._router.navigate(['user/'+sessionStorage.getItem('user-id')+ '/tasks'], {      
    })
  }

  getActiveTasks(){
    this._router.navigate(['user/'+ sessionStorage.getItem('user-id')+'/tasks/active'], {      
    })
  }

  createRequest(){  
    this._router.navigate(['/request/create/'], {
      queryParams: { userId:sessionStorage.getItem('user-id') }
    })
  }

}
