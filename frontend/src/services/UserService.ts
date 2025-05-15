import axios from "axios";

const REST_API_BASE_URL = "http://localhost:8080/api/users";

export interface User {
  id: number;
  firstName: string;
  lastName: string;
  username: string;
  email: string;
}

export const listUsers = () => axios.get<User[]>(REST_API_BASE_URL);
