import { Observable } from 'rxjs';
import { AuthService } from '../auth/auth.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';
import { EventService } from '../eventService/event.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit, OnDestroy {

  isLoggedIn$: Observable<boolean>;

  isPatient: boolean;
  isDoctor: boolean;
  isAdmin: boolean;
  private querySubscription: Subscription;
  userIdFromStorage:string;

  messageReceived: any;
  private subscriptionName: Subscription; //important to create a subscription


  constructor(
    private authService: AuthService,
    private _router : Router,
    private _eventService: EventService
  )
    {
        // subscribe to sender component messages
        this.subscriptionName= this._eventService.getUpdate().subscribe(
            message => { //message contains the data sent from service
              this.messageReceived = message;
              this.ngOnInit();
            }
         );

    }

  ngOnDestroy() { // It's a good practice to unsubscribe to ensure no memory leaks
    this.subscriptionName.unsubscribe();
  }

  ngOnInit() {
    console.log("HeaderComponent ngOnInit");

    this.isLoggedIn$ = this.authService.isLoggedIn;
    this.isPatient = (sessionStorage.getItem("isPatient")=="true");
    this.isDoctor = (sessionStorage.getItem("isDoctor")=="true");
    this.isAdmin = (sessionStorage.getItem("isAdmin")=="true");
    /* setInterval(() => {
                     this.ngOnInit(); // api call
                     console.log("HeaderComponent ngOnInit");
                  }, 1000); */

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
