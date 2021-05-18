import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {AngularMaterialModule} from './angular-material.module';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AuthGuard} from './auth/auth.guard';
import {AuthService} from './auth/auth.service';
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './login/login.component';

import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule, MatRippleModule} from '@angular/material/core';
import {MatButtonModule} from '@angular/material/button';
import {FlexLayoutModule} from '@angular/flex-layout';

import {UserComponent} from './user/user.component';
import {HttpClientModule} from "@angular/common/http";
import {AddUserComponent} from './user/add-user.component';
import {HeaderComponent} from "./header/header.component";
import {UserService} from "./user/user.service";
import {AddUserRequestComponent} from "./userRequest/add-userRequest.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RegistrationComponent} from './loginRegistration/components/registration/registration.component';
import {FooterComponent} from './footer/footer.component';
import {TaskComponent} from './task/task.component';
import {DatePipe} from "@angular/common";
import {TaskInstanceService} from './task/services/taskInstance.service';
import {TaskInstanceComponent} from './task-instance/task-instance/task-instance.component';
import {TaskInstanceActiveComponent} from './task-instance/task-instance-active/task-instance-active.component';
import {TaskInstanceActiveForUserComponent} from './task-instance/task-instance-active-for-user/task-instance-active-for-user.component';
import {TaskInstanceForUserComponent} from './task-instance/task-instance-for-user/task-instance-for-user.component';
import {PanelAdminComponent} from './panel-admin/panel-admin.component';
import {AccountComponent} from './account/account.component';
import {RecommendationDialog} from "./account/dialog/rec-dialog.component";
import {MatDialogModule} from "@angular/material/dialog";

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    AddUserComponent,
    AddUserRequestComponent,
    LoginComponent,
    HeaderComponent,
    HomeComponent,
    FooterComponent,
    RegistrationComponent,
    TaskComponent,
    TaskInstanceComponent,
    TaskInstanceActiveComponent,
    TaskInstanceActiveForUserComponent,
    TaskInstanceForUserComponent,
    PanelAdminComponent,
    AccountComponent,
    RecommendationDialog
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    HttpClientModule,
    MatSlideToggleModule,
    MatFormFieldModule,
    MatInputModule,
    BrowserAnimationsModule,
    AngularMaterialModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    FormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatRippleModule,
    MatButtonModule,
    FlexLayoutModule,
    MatDialogModule
  ],
  providers: [AuthService, AuthGuard, UserService, DatePipe, TaskInstanceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
