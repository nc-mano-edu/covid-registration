import { Component, OnInit } from "@angular/core";
import { Observable } from "rxjs";
import { AuthService } from "../auth/auth.service";

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {
  
  isLoggedIn$: Observable<boolean>;

  contentLeft: string[][] = [
    ["Personal treatment", "COVID Treatment Center MANO Group presents a wide range of services to maintain YOUR health."],
    ["Appointment with specialists", "Being a doctor has long become a duty, to which many have devoted their lives."]
  ];

  contentRight: string[][] = [
    ["Working schedule", `Monday — Friday; 8AM – 7PM`],
    ["Examination", "We work with experienced specialists forming an appointment schedule for each client."]
    
  ];

  constructor(private authService: AuthService){

  }

  ngOnInit() {
    this.isLoggedIn$ = this.authService.isLoggedIn;
  }

  onLogout() {
    this.authService.logout();
  }

}