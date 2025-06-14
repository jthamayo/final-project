import axiosAuth from "../util/axiosInstance";
import { REST_API_BASE_URL } from "../constants";
import { PublicUser } from "./UserService";

export const getUserNetwork = () => {
  return axiosAuth
    .get<PublicUser[]>(`${REST_API_BASE_URL}/api/user/connections`)
    .then((res) => res.data);
};
