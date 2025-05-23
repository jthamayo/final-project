import { useEffect, useState } from "react";
import { User, fetchUsers } from "../../api/users";

const ListUserComponent = () => {
  /*   const [users, setUsers] = useState(fetchUsers());
   */
  const [users, setUsers] = useState<User[]>([]);

  useEffect(() => {
    fetchUsers()
      .then((res) => setUsers(res.data))
      .catch((err) => console.error(err));
  }, []);

  return (
    <div className="">
      <h2 className="text-blue-600 text-5xl my-5">Usuarios</h2>
      <ul className="w-[400px] flex flex-col gap-1">
        {users.map((user, index) => (
          <li className="bg-blue-200 rounded-md w-full h-[50px] pl-5 flex items-center" key={index}>{`${user.firstName}, ${user.lastName}`}</li>
        ))}
      </ul>
    </div>
  );
};

export default ListUserComponent;
