import { useEffect, useState } from "react";
import { PublicUser, getPublicListUsers } from "../services/UserService.js";
import UserListItemComponent from "./UserListItemComponent.js";

const ListUserComponent = () => {
  const [users, setUsers] = useState<PublicUser[]>([]);

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const res = await getPublicListUsers();
        setUsers(res);
      } catch (err) {
        console.error("Failed to fetch users", err);
      }
    };
    fetchUsers();
  }, []);

  return (
    <div className="w-full">
      <ul className="flex flex-col gap-4 p-4">
        {users.map((user, index) => (
          <UserListItemComponent key={index} user={user} />
        ))}
      </ul>
    </div>
  );
};

export default ListUserComponent;
