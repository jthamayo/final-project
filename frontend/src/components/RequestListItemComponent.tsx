import { useEffect, useState } from "react";
import UserAvatar from "./user/UserAvatar.js";
import { Request } from "../services/RequestService.js";

const UserListItemComponent = ({ request }: { request: Request }) => {
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

  return (
    <li className="bg-white text-black rounded-md w-full flex items-center gap-2 justify-between p-4 h-30">
      <div className="h-full flex items-center justify-start gap-2">
        <UserAvatar url={request.userReceiver.profilePictureUrl} />
        <div className="flex gap-2 items-center">
          <h5 className="text-xl font-bold">
            {" "}
            {request.userReceiver.username}
          </h5>
          <p className="text-gray-400"> · {getNumberDays(request.sentDate)}</p>
        </div>
      </div>
      <div className="flex items-center gap-2">
        <button className="box p-0 aspect-square justify-center bg-green-300">
          <svg className="plus-icon size-7 text-muted flex items-center justify-center">
            <use xlinkHref="assets/icons.svg#tick"></use>
          </svg>
        </button>
        <button className="box p-0 aspect-square justify-center bg-red-300">
          <svg className="plus-icon size-7 text-muted flex items-center justify-center">
            <use xlinkHref="assets/icons.svg#cancel"></use>
          </svg>
        </button>
      </div>
    </li>
  );
};

export default UserListItemComponent;
