import axios from "axios";

export interface User {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
}

export const fetchUsers = () =>
  axios.get<User[]>("http://localhost:3000/users");
