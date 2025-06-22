import { useEffect, useState } from "react";
import { Network, getAllUserChats } from "../../services/ChatServices";
import { useAuth } from "../../context/useAuth";
import ChatListItemComponent from "./ChatListItemComponent";

const ChatsContainerComponent = () => {
  const [chats, setChats] = useState<Network[]>([]);
  const [loading, setLoading] = useState(true);
  const { currentUser } = useAuth();

  useEffect(() => {
    const fetchChats = async () => {
      try {
        const data = await getAllUserChats();
        setChats(data);
      } catch (error) {
        console.error("Error fetching chats:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchChats();
  }, []);

  if (loading) return <p>Loading chats...</p>;

  return (
    <section className="flex flex-col p-4 items-center h-full">
      <ul className="py-4 flex flex-col gap-2 w-full">
        {chats.length != 0 ? (
          chats.map((chat, index) => <ChatListItemComponent key={index} chat={chat} currentUser={currentUser}/>)
        ) : (
          <li className="text-red-500 box">You don't have any chats</li>
        )}
      </ul>
    </section>
  );
};

export default ChatsContainerComponent;
