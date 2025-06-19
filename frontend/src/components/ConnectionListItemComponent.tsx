import { useState } from "react";
import UserAvatar from "../components/user/UserAvatar.js";
import { PublicUser } from "../services/UserService.js";
import Modal from "../common/Modal.js";
import { addUserToGroup, Group } from "../services/GroupService.js";

const ConnectionListItemComponent = ({
  user,
  addParticipant,
}: {
  user: PublicUser;
  addParticipant: (updatedGroup: Group) => void;
}) => {
  const [showConfirm, setShowConfirm] = useState(false);

  const handleAddUserToGroup = async () => {
    try {
      const res = await addUserToGroup(user.username);
      addParticipant(res);
      setShowConfirm(false);
    } catch (err) {
      console.error("Failed to add user", err);
    }
  };

  return (
    <>
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
        {!user.hasGroup ? (
          <button
            onClick={() => setShowConfirm(true)}
            className="flex items-center gap-2 bg-blue-400 hover:bg-blue-500 p-2 px-4 rounded-lg mr-4 text-white"
          >
            Add {user.username} to your group
            <svg className="icon size-8">
              <use
                xlinkHref={`assets/icons.svg#add-user
              `}
              ></use>
            </svg>
          </button>
        ) : (
          <div className="bg-accent flex items-center p-2 rounded-md text-white">
            <svg className="icon size-8">
              <use xlinkHref="assets/icons.svg#users"></use>
            </svg>
            <svg className="icon size-8">
              <use xlinkHref="assets/icons.svg#tick"></use>
            </svg>
          </div>
        )}
      </li>
      {showConfirm && (
        <Modal onClose={() => setShowConfirm(false)}>
          <div className="p-4 flex flex-col items-center justify-evenly gap-4">
            <div className="w-40 border-8 rounded-xl bg-base border-base">
              <UserAvatar url={user.profilePictureUrl} />
            </div>
            <div className="text-gray-600">
              Are you sure You want to add{" "}
              <span className="font-bold text-base">{user.username}</span> to
              your group?
            </div>
            <div className="flex justify-center gap-4">
              <button
                onClick={handleAddUserToGroup}
                className="button w-20 bg-green-400 hover:bg-green-600"
              >
                Yes
              </button>
              <button
                onClick={() => setShowConfirm(false)}
                className="button bg-red-400 w-20 hover:bg-red-500"
              >
                No
              </button>
            </div>
          </div>
        </Modal>
      )}
    </>
  );
};

export default ConnectionListItemComponent;
