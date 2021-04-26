import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {AuthGuard} from './auth/auth.guard';
import {LoginComponent} from './login/login.component';
import {HomeComponent} from './home/home.component';
import {AddUserComponent} from "./user/add-user.component";
import {UserComponent} from "./user/user.component";
import {AddUserRequestComponent} from "./userRequest/add-userRequest.component";
import { MyLoginComponent } from './loginAndRegistration/components/my-login/my-login.component';
import { RegistrationComponent } from './loginAndRegistration/components/registration/registration.component';


const routes: Routes = [
 // {path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
  {path: 'home', component: HomeComponent},
  {path: 'login', component: MyLoginComponent},
 // {path: 'login', component: LoginComponent},
  {path: 'add', component: AddUserComponent},
  {path: 'users', component: UserComponent},
  {path: 'request/create', component: AddUserRequestComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: '**', redirectTo: 'home'},
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
