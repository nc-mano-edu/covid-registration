import {User} from "../../user/user.model";
import {Symptom} from "./symptom.model";

export class UserRequest {

  id: string;
  startDate: string;
  endDate: string;
  treatmentState: string;
  user: User;
  doctorRecommendations: string;
  symptoms: Symptom[];

}
