import { User } from "../../services/UserService";
import UserAvatar from "./UserAvatar";

const UserAccount = ({
  account,
  onLogout,
}: {
  account: User;
  onLogout: () => void;
}) => {
  return (
    <div className="">
      <button onClick={onLogout} className="w-full">
        <div className="bottom-full border-y border-gray-600 z-10 flex px-4 py-2 w-full text-left text-white justify-between items-center  hover:bg-gray-700">
          <p>Logout</p>

          <svg className="icon size-10">
            <use xlinkHref="/assets/icons.svg#logout"></use>
          </svg>
        </div>
      </button>
      <div className="details flex p-4 items-center justify-start gap-4 cursor-pointer">
        <div className="max-w-15">
          <UserAvatar url={account.profilePictureUrl} />
        </div>
        <div>
          <div className="text-xl text-gray-200">{account.username}</div>
          <div className="text-gray-400">{account.email}</div>
        </div>
      </div>
    </div>
  );
};

export default UserAccount;
