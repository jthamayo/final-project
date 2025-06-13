import { useEffect, useState } from "react";
import UserAvatar from "../components/user/UserAvatar.js";
import { requestNetwork } from "../services/RequestService.js";
import { PublicUser } from "../services/UserService.js";

const UserListItemComponent = ({ user }: { user: PublicUser }) => {
  const [requestSent, setRequestSent] = useState(false);
  const [error, setError] = useState(false);

  useEffect(() => {
    setRequestSent(user.hasPendingRequest);
  }, [user.hasPendingRequest]);

  const handleRequest = async () => {
    try {
      await requestNetwork(user.username);
      setRequestSent(true);
    } catch {
      setError(true);
      setTimeout(() => setError(false), 2000);
    }
  };

  return (
    <li className="bg-white text-black rounded-md w-full flex items-center justify-between p-4">
      <div className="flex items-center justify-evenly gap-4 h-25">
        <div className="bg-accent h-full aspect-square rounded-lg relative">
          <UserAvatar url={user.profilePictureUrl} />
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
        {error && (
          <p className="transition-opacity text-red-500 fade">
            Something went wrong. Please try again.
          </p>
        )}
        <button
          className={`flex items-center rounded-md p-3 ${
            requestSent ? "bg-green-300 cursor-default" : "bg-accent"
          }`}
          onClick={!requestSent ? handleRequest : undefined}
          disabled={requestSent}
        >
          {requestSent ? (
            <>
              <p>Request sent</p>
              <svg className="plus-icon size-7 text-muted flex items-center justify-center">
                <use xlinkHref="assets/icons.svg#tick"></use>
              </svg>
            </>
          ) : (
            <svg className="plus-icon size-7 text-muted flex items-center justify-center">
              <use xlinkHref="assets/icons.svg#add-user"></use>
            </svg>
          )}
        </button>
      </div>
    </li>
  );
};

export default UserListItemComponent;
