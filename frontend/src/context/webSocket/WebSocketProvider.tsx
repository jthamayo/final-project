import { useEffect, useState, ReactNode, useCallback } from "react";
import SockJS from "sockjs-client";
import { Client } from "@stomp/stompjs";
import { ACCESS_TOKEN, WEBSOCKET_BASE_URL } from "../../constants";
import { StompContext } from "./StompContext";
import { Message } from "../../services/MessageService";

const WebSocketProvider = ({ children }: { children: ReactNode }) => {
  const [client, setClient] = useState<Client | null>(null);

  const sendMessage = useCallback(
    (destination: string, body: Message) => {
      if (client?.connected) {
        client.publish({ destination, body: JSON.stringify(body) });
      } else {
        console.warn("Cannot send message: STOMP client not connected.");
      }
    },
    [client]
  );

  useEffect(() => {
    const socket = new SockJS(WEBSOCKET_BASE_URL);
    /*  const token = localStorage.getItem(ACCESS_TOKEN); */
    const stompClient = new Client({
      brokerURL: "ws://localhost:8080/ws"
       /*   connectHeaders: {
        Authorization: `Bearer ${token}`,
      }, */,
      reconnectDelay: 5000,
      debug: (str) => console.log("[///STOMP]", str),
      onConnect: () => {
        console.log("STOMP connected");
      },
      onStompError: (frame) => console.error("STOMP error:", frame),
    });

    stompClient.activate();
    setClient(stompClient);

    return () => {
      stompClient.deactivate();
    };
  }, []);

  return (
    <StompContext.Provider value={{ client, sendMessage }}>
      {children}
    </StompContext.Provider>
  );
};

export default WebSocketProvider;
