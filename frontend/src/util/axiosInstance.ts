import axios from "axios";
import { REST_API_BASE_URL, ACCESS_TOKEN } from "../constants/index.ts";

const axiosInstance = axios.create({
  baseURL: REST_API_BASE_URL,
});

axiosInstance.interceptors.request.use((config) => {
  const token = localStorage.getItem(ACCESS_TOKEN);
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export default axiosInstance;
