import axios from "../util/axiosInstance";

export interface User {
  id: number;
  firstName: string;
  lastName: string;
  username: string;
  email: string;
}

export const listUsers = () =>
  axios.get<User[]>(`/api/users`).then((res) => res.data);


export const getCurrentUser = () => {
  return axios.get<User>("/api/user/me").then((res) => res.data);
};
