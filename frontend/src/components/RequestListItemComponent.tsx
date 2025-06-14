import { useState } from "react";
import UserAvatar from "./user/UserAvatar.js";
import { Request } from "../services/RequestService.js";
import { acceptRequest, rejectRequest } from "../services/RequestService.js";

const UserListItemComponent = ({
  request,
  isReceived,
}: {
  request: Request;
  isReceived: boolean;
}) => {
  type Status = "pending" | "accepted" | "rejected";
  const [status, setStatus] = useState<Status>("pending");
  const user = isReceived ? request.userSender : request.userReceiver;

  const getNumberDays = (sentDate: string | Date) => {
    const sent = new Date(sentDate);
    const now = new Date();
    const days = Math.floor(
      (now.getTime() - sent.getTime()) / (1000 * 60 * 60 * 24)
    );
    if (days <= 0) return "Today";
    if (days === 1) return "1 day ago";
    return `${days} days ago`;
  };

  const handleAccept = async () => {
    try {
      await acceptRequest(request.id);
      setStatus("accepted");
    } catch (error) {
      console.error("Accept failed:", error);
    }
  };

  const handleReject = async () => {
    try {
      await rejectRequest(request.id);
      setStatus("rejected");
    } catch (error) {
      console.error("Reject failed:", error);
    }
  };

  const resolveMessage =
    status === "accepted"
      ? "You have accepted the request"
      : status === "rejected"
      ? "You have rejected the request"
      : null;

  return (
    <li className="bg-white text-black rounded-md w-full flex items-center gap-2 justify-between p-4 h-30">
      <div className="h-full flex items-center justify-start gap-2">
        <UserAvatar url={user.profilePictureUrl} />
        <div className="flex gap-2 items-center">
          <h5 className="text-xl font-bold">{user.username}</h5>
          <p className="text-gray-400"> · {getNumberDays(request.sentDate)}</p>
          {status !== "pending" && resolveMessage && (
            <p className="text-gray-400 italic">{resolveMessage}</p>
          )}
        </div>
      </div>
      {isReceived && (
        <div className="flex items-center gap-2">
          <button
            onClick={handleAccept}
            className={`box p-0 aspect-square justify-center ${
              status === "rejected" ? "bg-gray-300 opacity-20" : "bg-green-300"
            }`}
            disabled={status !== "pending"}
          >
            <svg className="plus-icon size-7 text-muted flex items-center justify-center">
              <use xlinkHref="assets/icons.svg#tick"></use>
            </svg>
          </button>
          <button
            onClick={handleReject}
            className={`box p-0 aspect-square justify-center ${
              status === "accepted" ? "bg-gray-300 opacity-20" : "bg-red-300"
            }`}
            disabled={status !== "pending"}
          >
            <svg className="plus-icon size-7 text-muted flex items-center justify-center">
              <use xlinkHref="assets/icons.svg#cancel"></use>
            </svg>
          </button>
        </div>
      )}
    </li>
  );
};

export default UserListItemComponent;
