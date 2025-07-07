import { useContext, useEffect, useState } from "react";
import { Message } from "../../services/MessageService";
import { StompContext } from "./StompContext";

export const useStomp = (topics: string[]) => {
  const context = useContext(StompContext);
  const [messages, setMessages] = useState<Message[]>([]);

  if (!context) {
    throw new Error("useStomp must be used within a WebSocketProvider");
  }

  const { client, sendMessage } = context;

  useEffect(() => {
    if (!client || !client.connected) return;

    const subscriptions = topics.map((topic) =>
      client!.subscribe(topic, (m) => {
        try {
          const body = JSON.parse(m.body);
          setMessages((prev) => [...prev, body]);
        } catch (err) {
          console.error("Invalid STOMP message", err);
        }
      })
    );

    return () => {
      subscriptions.forEach((sub) => sub.unsubscribe());
    };
  }, [client, topics]);

  return { messages, sendMessage };
};
