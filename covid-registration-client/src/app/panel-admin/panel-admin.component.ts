import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Subscription} from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-panel-admin',
  templateUrl: './panel-admin.component.html',
  styleUrls: ['./panel-admin.component.css']
})
export class PanelAdminComponent implements OnInit {

  userId: number;
  user15Id = 15;
  private querySubscription: Subscription;
  userIdFromStorage =sessionStorage.getItem('user-id');  

  constructor(
    public activatedRoute: ActivatedRoute,
    private route: ActivatedRoute,
    private _router : Router
    
  ) {   }

  ngOnInit(): void {
    
  }

  createRequest(){  
    this._router.navigate(['/request/create/'], {
      queryParams: { userId: this.userIdFromStorage }
    })

  }

  getAllTasks() {    
    this._router.navigate(['user/'+this.userIdFromStorage+ '/tasks'], {      
    })
  }

  getActiveTasks(){
    this._router.navigate(['user/'+ this.userIdFromStorage+'/tasks/active'], {      
    })
  }


  emptyTasks(){
    let userIdFromStorage =sessionStorage.getItem('user-id');  

    this._router.navigate(['/user/'+ userIdFromStorage], {
      
    })
  }

}


