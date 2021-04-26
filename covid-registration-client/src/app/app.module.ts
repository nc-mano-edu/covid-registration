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
import {FlexLayoutModule} from '@angular/flex-layout';

import {UserComponent} from './user/user.component';
import {HttpClientModule} from "@angular/common/http";
import {AddUserComponent} from './user/add-user.component';
import {HeaderComponent} from "./header/header.component";
import {UserService} from "./user/user.service";
import {AddUserRequestComponent} from "./userRequest/add-userRequest.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {FooterComponent} from './footer/footer.component';
import {TaskComponent} from './task/task.component';
import {DatePipe} from "@angular/common";


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
    TaskComponent
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
    FlexLayoutModule
  ],
  providers: [AuthService, AuthGuard, UserService, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
