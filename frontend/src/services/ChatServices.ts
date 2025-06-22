import axiosAuth from "../util/axiosInstance";
import { REST_API_BASE_URL } from "../constants";
import { Message } from "./MessageService";
import { PublicUser } from "./UserService";

export interface Chat {
  id: number;
  chatId: number;
  messages: Message[];
  participants: PublicUser[];
}
export interface Network extends Chat {
  networkId: number;
}

export const getAllUserChats = () => {
  return axiosAuth
    .get<Network[]>(`${REST_API_BASE_URL}/api/user/chats`)
    .then((res) => res.data);
};

export const getUseGroupChat = () => {
  return axiosAuth
    .get<Chat>(`${REST_API_BASE_URL}/api/user/groupchat`)
    .then((res) => res.data);
};
