import { useEffect, useState } from "react";
import { PublicUser } from "../services/UserService";
import { getUserNetwork } from "../services/NetworkService";
import ConnectionListItemComponent from "./ConnectionListItemComponent";

const ListConnectedComponent = () => {
  const [friends, setFriends] = useState<PublicUser[]>([]);
  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const res = await getUserNetwork();
        setFriends(res);
      } catch (err) {
        console.error("Failed to fetch users", err);
      }
    };
    fetchUsers();
  }, []);

  useEffect(() => {}, []);
  return (
    <section className="p-4">
      <h4>Friends</h4>
      <ul className="py-4 flex flex-col gap-2">
        {friends.map((friend, index) => (
          <ConnectionListItemComponent key={index} user={friend} />
        ))}
      </ul>
    </section>
  );
};

export default ListConnectedComponent;
