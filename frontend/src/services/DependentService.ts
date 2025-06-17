import axiosAuth from "../util/axiosInstance";
import { REST_API_BASE_URL } from "../constants";

export type Allergy =
  | "peanuts"
  | "tree_nuts"
  | "milk"
  | "eggs"
  | "gluten"
  | "insect_stings"
  | "medications"
  | "other";

export interface Dependent {
  firstName: string;
  lastName: string;
  allergies: Allergy[];
  isSpecialNeeds: boolean;
  isAllergic: boolean;
  birthDate: string;
}

export const AllergyOptions: Allergy[] = [
  "peanuts",
  "tree_nuts",
  "milk",
  "eggs",
  "gluten",
  "insect_stings",
  "medications",
  "other",
];

export const addDependent = (edited: Dependent) => {
  return axiosAuth
    .post<Dependent>(`${REST_API_BASE_URL}/api/user/add/dependents`, edited)
    .then((res) => res.data);
};
