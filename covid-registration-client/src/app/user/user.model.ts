import {Role} from "./role.model";
export class User {

  id: string;
  firstName: string;
  middleName: string;
  lastName: string;
  dateOfBirth: string;
  insuranceNumber: string;
  phoneNumber: string;
  username: string;
  password: string;
  email: string;
  roles: Role[];

}
