import axiosAuth from "../util/axiosInstance";
import { REST_API_BASE_URL } from "../constants";

export interface Vehicle {
  licensePlate: string;
  model: string;
  color: string;
  chassisNumber: string;
}

export const addVehicle = (edited: Vehicle) => {
  return axiosAuth
    .post<Vehicle>(`${REST_API_BASE_URL}/api/user/add/vehicle`, edited)
    .then((res) => res.data);
};

