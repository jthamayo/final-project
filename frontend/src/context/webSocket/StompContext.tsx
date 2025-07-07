import { Client } from "@stomp/stompjs";
import { createContext } from "react";
import { Message } from "../../services/MessageService";

interface WebSocketContextType {
  client: Client | null;
  sendMessage: (destination: string, body: Message) => void;
}

export const StompContext = createContext<WebSocketContextType | undefined>(
  undefined
);
