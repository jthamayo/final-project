import { useState } from "react";
import { User } from "../../services/UserService";
import UserAvatar from "./UserAvatar";

const UserAccount = ({
  account,
  onLogout,
}: {
  account: User;
  onLogout: () => void;
}) => {
  const [isOpen, setIsOpen] = useState(true);
  const toggleDropdown = () => setIsOpen((prev) => !prev);
  return (
    <div className="relative">
      <div
        className="details border-2 border-gray-200 flex p-4 items-center justify-start gap-4 cursor-pointer"
        onClick={toggleDropdown}
      >
        <UserAvatar url={account.profilePictureUrl} />
        <div>
          <div className="text-xl">{account.username}</div>
          <div className="text-gray-400">{account.email}</div>
        </div>
      </div>

      {isOpen && (
        <div className="absolute right-0 bottom-full w-full border border-gray-200 z-10">
          <button
            onClick={onLogout}
            className="w-full text-left px-4 py-2 hover:bg-gray-100"
          >
            Logout
          </button>
        </div>
      )}
    </div>
  );
};

export default UserAccount;
