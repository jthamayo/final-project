import { useState } from "react";
import UserAvatar from "../../components/user/UserAvatar.js";
import { PublicUser } from "../../services/UserService.js";

const GroupCandidateListItemComponent = ({
  user,
  onClick,
}: {
  user: PublicUser;
  onClick: () => void;
}) => {
  const [isSelected, setIsSelected] = useState(false);

  const handleClick = () => {
    onClick();
    setIsSelected((prev) => !prev);
  };

  return (
    <li
      className={`text-black rounded-md w-full flex items-center justify-between p-4 gap-12 relative ${
        isSelected ? " bg-blue-200" : " bg-box"
      }`}
    >
      <div className="flex items-center justify-evenly gap-4 h-15">
        <div className="bg-accent h-full aspect-square rounded-lg relative">
          <UserAvatar url={user.profilePictureUrl} />
        </div>
        <div className="flex flex-col justify-evenly h-full">
          <h5 className="text-2xl">{user.username || "unknown"}</h5>
          <p className="text-gray-500">{user.email}</p>
        </div>
      </div>
      <div className="flex items-center gap-2 absolute top-2 right-2">
        <button
          onClick={handleClick}
          className={`${
            isSelected
              ? "bg-red-300 hover:bg-red-400"
              : "bg-green-300 hover:bg-green-400"
          } size-12 rounded-sm flex justify-center items-center`}
        >
          <svg className="icon size-8">
            <use
              xlinkHref={`/assets/icons.svg#${
                isSelected ? "delete-user" : "add-user"
              }`}
            ></use>
          </svg>
        </button>
      </div>
    </li>
  );
};

export default GroupCandidateListItemComponent;
