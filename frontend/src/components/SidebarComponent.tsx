import { useState } from "react";
import UserAccount from "../components/user/UserAccount";
import { User } from "../services/UserService";
import { useNavigate } from "react-router-dom";
import UserAvatar from "./user/UserAvatar";

const SidebarComponent = ({
  onLogout,
  currentUser,
}: {
  onLogout: () => void;
  currentUser: User;
}) => {
  const navigate = useNavigate();
  const [sidebarOpen, setSidebarOpen] = useState(false);
  const handleNavigate = (path: string) => {
    navigate(path);
    setSidebarOpen(false);
  };
  return (
    <>
      <div className="bg-base rounded-b-full fixed top-0 left-0 w-full h-15 flex items-center justify-center md:hidden z-10 gap-2 p-2">
        <button
          className="p-1 z-50 bg-white rounded-md"
          aria-label="Open menu"
          onClick={() => setSidebarOpen(true)}
        >
          <svg className="size-8">
            <use xlinkHref="/assets/icons.svg#menudashboard"></use>
          </svg>
        </button>
        <div className="h-full flex p-1 gap-2">
          <UserAvatar url={currentUser.profilePictureUrl} />
          <div className="flex flex-col items-start justify-center">
            <p className="text-white text-xl">{currentUser.username}</p>
            <p className="text-gray-500">{currentUser.email}</p>
          </div>
        </div>
      </div>
      <aside
        className={`fixed top-0 left-0 h-full bg-base shadow-md flex flex-col justify-between
          w-64 z-50 transform transition-transform duration-300 ease-in-out max-sm:w-full
          ${sidebarOpen ? "translate-x-0" : "-translate-x-full"}
          md:translate-x-0 md:static md:w-1/4`}
      >
        <div className="flex justify-start items-center px-4 bg-white">
          <img className="size-10" src="/assets/logo_white.png" alt="logo" />
          <h4 className="text-base flex p-4 items-center justify-start gap-4">
            Dashboard
          </h4>
        </div>
        <div className="p-4 h-full flex flex-col justify-start gap-8">
          <nav>
            <ul className="flex flex-col gap-1">
              <li>
                <button
                  className="cell"
                  onClick={() => handleNavigate("/dashboard/profile")}
                >
                  <p>Profile</p>
                  <svg className="icon size-10">
                    <use xlinkHref="/assets/icons.svg#star"></use>
                  </svg>
                </button>
              </li>
              <li>
                <button
                  className="cell"
                  onClick={() => handleNavigate("/dashboard/search")}
                >
                  <p>Search</p>
                  <svg className="icon size-10">
                    <use xlinkHref="/assets/icons.svg#search"></use>
                  </svg>
                </button>
              </li>
              <li>
                <button
                  className="cell"
                  onClick={() => handleNavigate("/dashboard/settings")}
                >
                  <p>Settings</p>
                  <svg className="icon size-10">
                    <use xlinkHref="/assets/icons.svg#settings"></use>
                  </svg>
                </button>
              </li>
              <li>
                <button
                  className="cell"
                  onClick={() => handleNavigate("/dashboard")}
                >
                  <p>Schedule</p>
                  <svg className="icon size-10">
                    <use xlinkHref="/assets/icons.svg#calendar"></use>
                  </svg>
                </button>
              </li>
              <li>
                <button
                  className="cell"
                  onClick={() => handleNavigate("/dashboard/chats")}
                >
                  <p>Chats</p>
                  <svg className="icon size-10">
                    <use xlinkHref="/assets/icons.svg#message"></use>
                  </svg>
                </button>
              </li>
              <li>
                <button
                  className="cell"
                  onClick={() => handleNavigate("/dashboard/friends")}
                >
                  <p>Friends</p>
                  <svg className="icon size-10">
                    <use xlinkHref="/assets/icons.svg#users"></use>
                  </svg>
                </button>
              </li>
              <li>
                <button
                  className="cell"
                  onClick={() => handleNavigate("/dashboard/requests")}
                >
                  <p>Requests</p>
                  <svg className="icon size-10">
                    <use xlinkHref="/assets/icons.svg#notification"></use>
                  </svg>
                </button>
              </li>
            </ul>
          </nav>
        </div>
        <UserAccount account={currentUser} onLogout={onLogout} />
      </aside>
    </>
  );
};

export default SidebarComponent;
