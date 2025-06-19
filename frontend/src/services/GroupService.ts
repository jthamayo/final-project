import { REST_API_BASE_URL } from "../constants";
import axiosAuth from "../util/axiosInstance";
import { PublicUser } from "./UserService";

export interface Group {
  users: PublicUser[];
}

export const getGroupCandidates = () => {
  return axiosAuth
    .get<PublicUser[]>(`${REST_API_BASE_URL}/api/user/group/candidates`)
    .then((res) => res.data);
};

export const addGroup = (selected: string[]) => {
  return axiosAuth
    .post<Group>(`${REST_API_BASE_URL}/api/user/add/group`, selected)
    .then((res) => res.data);
};

export const getGroup = () => {
  return axiosAuth
    .get<Group>(`${REST_API_BASE_URL}/api/user/group`)
    .then((res) => res.data);
};

export const addUserToGroup = (username: string) => {
  return axiosAuth
    .post(`${REST_API_BASE_URL}/api/user/group/add/${username}`)
    .then((res) => res.data);
};
