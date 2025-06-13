import axiosAuth from "../util/axiosInstance";
import { REST_API_BASE_URL } from "../constants";
import { User } from "./UserService";

export interface Request {
  id: number;
  sentDate: Date;
  userSender: User;
  userReceiver: User;
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
