import axiosAuth from "../util/axiosInstance";
import { REST_API_BASE_URL } from "../constants";
import { PublicUser } from "./UserService";

export interface Request {
  id: number;
  sentDate: Date;
  userSender: PublicUser;
  userReceiver: PublicUser;
  status: string;
}

export const requestNetwork = (username: string) => {
  return axiosAuth
    .post(`${REST_API_BASE_URL}/api/user/connect/${username}`)
    .then((res) => {
      return res.data;
    });
};

export const getReceivedNetworkRequests = () => {
  return axiosAuth
    .get<Request[]>(`${REST_API_BASE_URL}/api/user/requests/received/pending`)
    .then((res) => {
      return res.data;
    });
};

export const getSentNetworkRequests = () => {
  return axiosAuth
    .get<Request[]>(`${REST_API_BASE_URL}/api/user/requests/sent/pending`)
    .then((res) => res.data);
};

export const rejectRequest = (id: number) => {
  return axiosAuth
    .put<Request[]>(`${REST_API_BASE_URL}/api/user/requests/reject/${id}`)
    .then((res) => res.data);
};

export const acceptRequest = (id: number) => {
  return axiosAuth
    .post<Request[]>(`${REST_API_BASE_URL}/api/user/requests/accept/${id}`)
    .then((res) => res.data);
};
