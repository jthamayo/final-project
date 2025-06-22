import { useEffect, useState } from "react";
import { Network } from "../../services/ChatServices";
import Modal from "../../common/Modal";
import UserAvatar from "../user/UserAvatar";
import { PublicUser, User } from "../../services/UserService";
import ChatComponent from "./ChatComponent";

const ChatListItemComponent = ({
  chat,
  currentUser,
}: {
  chat: Network;
  currentUser: User | null;
}) => {
  const [openChat, setOpenChat] = useState(false);
  const [recipient, setRecipient] = useState<PublicUser | null>(null);

  useEffect(() => {
    const recipientUser = chat.participants.find(
      (participant) => participant.username !== currentUser?.username
    );
    setRecipient(recipientUser || null);
  }, [chat, currentUser]);

  const hanldeClick = () => {
    setOpenChat(true);
  };

  if (recipient == null || currentUser == null) {
    return null;
  }

  return (
    <>
      <li className="cell rounded-lg h-30 p-4 relative" onClick={hanldeClick}>
        <div className="h-full flex gap-4">
          <UserAvatar url={recipient.profilePictureUrl} />

          <div className="">
            <p className="text-xl">{recipient.username}</p>
            <p className="text-gray-600">{recipient.email}</p>
            {chat.messages.length != 0 ? (
              <p className="text-gray-400 text-left truncate max-w-[200px] sm:max-w-md">
                {`${
                  chat.messages[chat.messages.length - 1].senderUsername ===
                  currentUser.username
                    ? "You"
                    : chat.messages[chat.messages.length - 1].senderUsername
                } : 
                ${chat.messages[chat.messages.length - 1].content}`}
              </p>
            ) : (
              <p className="text-gray-400 text-left ">No messages yet</p>
            )}
          </div>
        </div>
        <div className="absolute top-4 right-4">
          <svg className="icon size-12">
            <use xlinkHref="/assets/icons.svg#message"></use>
          </svg>
        </div>
      </li>
      {openChat && (
        <Modal onClose={() => setOpenChat(false)}>
          <ChatComponent
            conversation={chat}
            partner={recipient}
            currentUser={currentUser}
          />
        </Modal>
      )}
    </>
  );
};

export default ChatListItemComponent;
