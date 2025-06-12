import { ACCESS_TOKEN, REST_API_BASE_URL } from "../constants";
import axiosAuth from "../util/axiosInstance";
import axios from "axios";
import { Address } from "./AddressService";
import { Job } from "./JobService";
import { Vehicle } from "./VehicleService";


export interface PublicUser {
  firstName: string;
  lastName: string;
  username: string;
  email: string;
  isVerified: boolean;
  profilePictureUrl: string;
  hasPendingRequest: boolean;
}

export interface User extends PublicUser{
  id: number;
}

export interface UserProfile extends User {
  phoneNumber: string;
  vehicle: Vehicle;
  address: Address;
  jobs: Job[];
  profilePictureUrl: string;
}

export type ProfilePictureProps = { url: string };

export const getListUsers = () => {
  return axios
    .get<User[]>(`${REST_API_BASE_URL}/api/users`)
    .then((res) => res.data);
};

export const getPublicListUsers = () => {
  return axiosAuth
    .get<User[]>(`${REST_API_BASE_URL}/api/user/me/search`)
    .then((res) => res.data);
};

export const getCurrentUser = () => {
  return axiosAuth.get<User>("/api/user/me").then((res) => res.data);
};

export const getProfileInformation = () => {
  return axiosAuth
    .get<UserProfile>("/api/user/me/profile")
    .then((res) => res.data);
};

export const updateUserProfile = (updatedProfile: UserProfile) => {
  return axiosAuth
    .put(`${REST_API_BASE_URL}/api/user/me/profile/edit`, updatedProfile)
    .then((res) => {
      return res.data;
    });
};

export const uploadProfilePicture = (file: File) => {
  const formData = new FormData();
  formData.append("file", file);
  return axiosAuth
    .post(`${REST_API_BASE_URL}/api/user/me/profile/picture`, formData)
    .then((res) => {
      return res.data;
    });
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
