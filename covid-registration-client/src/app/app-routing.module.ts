import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {AuthGuard} from './auth/auth.guard';
import {LoginComponent} from './login/login.component';
import {HomeComponent} from './home/home.component';
import {AddUserComponent} from "./user/add-user.component";
import {UserComponent} from "./user/user.component";
import {AddUserRequestComponent} from "./userRequest/add-userRequest.component";
import { RegistrationComponent } from './loginRegistration/components/registration/registration.component';
import { NewLoginComponent } from './loginRegistration/components/new-login/new-login.component';
import {TaskComponent} from "./task/task.component";

import { AccountComponent } from './account/account.component';
import {TaskInstanceComponent} from './task-instance/task-instance/task-instance.component';
import {TaskInstanceActiveComponent} from './task-instance/task-instance-active/task-instance-active.component';
import {TaskInstanceForUserComponent} from './task-instance/task-instance-for-user/task-instance-for-user.component';
import {TaskInstanceActiveForUserComponent} from './task-instance/task-instance-active-for-user/task-instance-active-for-user.component';


const routes: Routes = [
  {path: 'registration', component: RegistrationComponent},
  {path: 'login', component: NewLoginComponent},
  {path: 'home', component: HomeComponent/*, canActivate: [AuthGuard]*/},
 // {path: 'login', component: LoginComponent},
  {path: 'account', component: AccountComponent},
  {path: 'add', component: AddUserComponent},
  {path: 'users', component: UserComponent},
  {path: 'user/:id/tasks', component: TaskInstanceForUserComponent},
  {path: 'user/:id/tasks/active', component: TaskInstanceActiveForUserComponent},
  {path: 'request/create', component: AddUserRequestComponent},
  {path: 'task/:task_id', component: TaskComponent},
  {path: 'task_instances', component: TaskInstanceComponent},
  {path: 'task_instances/active', component: TaskInstanceActiveComponent},
  {path: '**', redirectTo: 'home'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
