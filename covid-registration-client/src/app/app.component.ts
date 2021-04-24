import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from './auth/auth.service';

@Component({
  selector: 'app-root',
  templateUrl:'./app.component.html',
  styles: []
})
export class AppComponent {

  isOnLogginPage$: Observable<boolean>;
  isOnLogginPage:boolean;
  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.isOnLogginPage$ = this.authService.isOnLogginPage;
    this.isOnLogginPage$.subscribe((item)=>{this.isOnLogginPage = item})
  }

}
