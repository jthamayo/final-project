import { PublicUser } from "../../services/UserService";
import UserAvatar from "../user/UserAvatar";

const GroupItemComponent = ({
  participants,
}: {
  participants: PublicUser[];
}) => {
  return (
    <div className="flex justify-start h-35 p-4 gap-4 items-center rounded-lg bg-white my-4">
      <div className="h-full flex">
        {participants.map((user, index) => (
          <div
            key={index}
            className={`${
              index !== 0 ? "-ml-15" : ""
            } border-4 border-white bg-white rounded-lg hover:z-40`}
            style={{ zIndex: participants.length - index }}
          >
            <UserAvatar url={user.profilePictureUrl} />
          </div>
        ))}
      </div>
      <div>
        <p className="text-xl">Members</p>{" "}
        <ul className="flex flex-col md:gap-2 text-gray-400 md:flex-row">
          {participants.map((user, index) => (
            <li key={index}>
              {user.username}{" "}
              {index != participants.length - 1 && <span>·</span>}
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default GroupItemComponent;
