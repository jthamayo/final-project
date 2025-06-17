import axiosAuth from "../util/axiosInstance";
import { REST_API_BASE_URL } from "../constants";
import { Address } from "./AddressService";

export interface Job {
  isIrregular: boolean;
  isNocturnal: boolean;
}

export interface JobWithAddress {
  job: Job;
  address: Address;
}

export const addJob = (edited: JobWithAddress) => {
  return axiosAuth
    .post<JobWithAddress>(`${REST_API_BASE_URL}/api/user/add/job`, edited)
    .then((res) => res.data);
};
