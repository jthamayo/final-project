import { useState } from "react";
import { Message } from "../../services/MessageService";
import { Network } from "../../services/ChatServices";
import { PublicUser, User } from "../../services/UserService";
import UserAvatar from "../user/UserAvatar";
import { sendMessageToUser } from "../../services/MessageService";

const ChatComponent = ({
  conversation,
  partner,
  currentUser,
}: {
  conversation: Network;
  partner: PublicUser;
  currentUser: User;
}) => {
  const [messages, setMessages] = useState<Message[]>(conversation.messages);
  const [newMessage, setNewMessage] = useState("");

  const handleSend = async () => {
    if (!newMessage.trim()) return;

    const messagePayload = {
      content: newMessage,
      senderUsername: currentUser.username,
      chatId: conversation.networkId,
      sentAt: new Date().toISOString().slice(0, -1),
    };

    try {
      const savedMessage = await sendMessageToUser(messagePayload);
      setMessages((prev) => [...prev, savedMessage]);
      setNewMessage("");
    } catch (error) {
      console.error("Failed to send message:", error);
    }
  };

  return (
    <div className="p-4 flex flex-col items-center justify-evenly gap-4 bg-base rounded-md w-full relative">
      <div className="h-20 flex gap-4 border-b border-gray-600 p-2 absolute top-0 w-full justify-start">
        <UserAvatar url={partner.profilePictureUrl} />
        <div>
          <p className="text-white text-2xl">{partner.username}</p>
          <p className="text-gray-500">{partner.email}</p>
        </div>
      </div>
      <ol className="overflow-y-auto my-20 min-h-120 w-full flex flex-col justify-start items-center text-white relative gap-2">
        {messages.map((message, index) => (
          <li
            key={index}
            className={`p-1 px-6 rounded-md max-w-2/3 ${
              message.senderUsername == currentUser.username
                ? "bg-blue-500 self-end text-right"
                : "self-start bg-gray-500"
            }`}
          >
            <p className="fit-content text">{message.content}</p>
          </li>
        ))}
        {messages.length === 0 && (
          <p className="text-center absolute left-1/2 top-1/2 -translate-1/2 w-full">
            This conversation is empty, write the first message!
          </p>
        )}
      </ol>

      <div className="flex justify-center gap-4 w-full absolute bottom-4 px-4">
        <input
          className="border border-gray-700 rounded-md text-white flex-1 p-2 bg-gray-700"
          type="text"
          value={newMessage}
          onChange={(e) => setNewMessage(e.target.value)}
          onKeyDown={(e) => e.key === "Enter" && handleSend()}
        />
        <button onClick={handleSend}>
          <svg className="icon size-8 text-white">
            <use xlinkHref="/assets/icons.svg#send"></use>
          </svg>
        </button>
      </div>
    </div>
  );
};

export default ChatComponent;
