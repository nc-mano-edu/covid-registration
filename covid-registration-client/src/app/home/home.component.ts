import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls:['./home.component.css']
})
export class HomeComponent {

  
  isPatient: boolean;
  isDoctor: boolean;
  isAdmin: boolean;
  
  constructor(

    private _router : Router
  ) { }

  ngOnInit(): void {
    this.isPatient = (sessionStorage.getItem("isPatient")=="true");
    this.isDoctor = (sessionStorage.getItem("isDoctor")=="true");
    this.isAdmin = (sessionStorage.getItem("isAdmin")=="true");

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

