import axiosAuth from "../util/axiosInstance";
import { REST_API_BASE_URL } from "../constants";

export type AddressType = "home" | "work";
export interface Address {
  city: string;
  street: string;
  zip: string;
  country: string;
  number: number;
  type: AddressType;
}

export const addAddress = (edited: Address) => {
  return axiosAuth
    .post<Address>(`${REST_API_BASE_URL}/api/user/add/address`, edited)
    .then((res) => res.data);
};
