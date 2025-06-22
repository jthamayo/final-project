import axiosAuth from "../util/axiosInstance";
import { REST_API_BASE_URL } from "../constants";

export interface Message{
    content: string;
    senderUsername: string;
    chatId: number;
    sentAt: string;
}

export const sendMessageToUser = (message: Message) => {
  return axiosAuth
    .post<Message>(`${REST_API_BASE_URL}/api/user/message/send/user`, message)
    .then((res) => res.data);
};