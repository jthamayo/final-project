import { useEffect, useState } from "react";
import { User, getListUsers } from "../../services/UserService.js";

const ListUserComponent = () => {
  const [users, setUsers] = useState<User[]>([]);

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const res = await getListUsers();
        setUsers(res);
      } catch (err) {
        console.error("request failed", err);
        throw err;
      }
    };
    fetchUsers();
  }, []);

  return (
    <div className="w-full">
      <ul className="flex flex-col gap-4 p-4">
        {users.map((user, index) => (
          <li
            className="bg-white text-black rounded-md w-full flex items-center justify-between p-4"
            key={index}
          >
            <div className="flex items-center justify-evenly gap-4 h-25">
              <div className="bg-accent h-full aspect-square rounded-lg relative">
                Avatar
                <div
                  className={`${
                    user.isVerified ? "bg-blue-300" : "bg-red-300"
                  } h-6 aspect-square rounded-full absolute -top-1 -right-2`}
                ></div>
              </div>
              <div className="flex flex-col justify-evenly h-full">
                <h5 className="text-2xl">{user.username || "unknown"}</h5>
                <p className="text-gray-500">{user.email}</p>
              </div>
            </div>
            <div className="flex items-center gap-2">
              <button className="bg-accent rounded-md p-3  hover:bg-red-300">
                Watch profile
              </button>
              <button className="bg-accent rounded-md p-3  hover:bg-red-300">
                Send Request
              </button>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ListUserComponent;
