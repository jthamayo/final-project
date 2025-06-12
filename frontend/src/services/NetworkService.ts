import axiosAuth from "../util/axiosInstance";
import { REST_API_BASE_URL } from "../constants";

export const requestNetwork = (username: string) => {
  return axiosAuth
    .post(`${REST_API_BASE_URL}/api/user/connect/${username}`)
    .then((res) => {
      return res.data;
    });
};

export const getNetworkRequests = () => {
  return axiosAuth
    .get(`${REST_API_BASE_URL}/api/user/requests/pending`)
    .then((res) => {
      return res.data;
    });
};
