import UserAvatar from "../components/user/UserAvatar.js";
import { PublicUser } from "../services/UserService.js";


const ConnectionListItemComponent = ({ user }: { user: PublicUser }) => {
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
        
      </div>
    </li>
  )
}

export default ConnectionListItemComponent