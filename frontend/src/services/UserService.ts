import { ACCESS_TOKEN, REST_API_BASE_URL } from "../constants";
import axiosAuth from "../util/axiosInstance";
import axios from "axios";

export interface User {
  id: number;
  firstName: string;
  lastName: string;
  username: string;
  email: string;
}

export const listUsers = () =>
  axiosAuth.get<User[]>(`/api/users`).then((res) => res.data);

export const getCurrentUser = () => {
  return axiosAuth.get<User>("/api/user/me").then((res) => res.data);
};

export interface Login {
  usernameOrEmail: string;
  password: string;
}

export const loginUser = (credentials: Login) => {
  return axios
    .post(`${REST_API_BASE_URL}/api/auth/login`, credentials)
    .then((res) => {
      localStorage.setItem(ACCESS_TOKEN, res.data.accessToken);
      return res.data;
    });
};

export interface Signup {
  firstName: string;
  lastName: string;
  username: string;
  email: string;
  phoneNumber: string;
  password: string;
}

export const signupUser = (userData: Signup) => {
  return axios
    .post(`${REST_API_BASE_URL}/api/auth/signup`, userData)
    .then((res) => {
      return res.data;
    });
};
