import { useEffect, useState } from "react";
import { useAuth } from "../context/useAuth";
import { useNavigate } from "react-router-dom";
import UserAccount from "../components/user/UserAccount";
import ListUserComponent from "../components/ListUserComponent";
import ProfileComponent from "../components/user/ProfileComponent";
import { SettingsComponent } from "../components/SettingsComponent";
import ListRequestsComponent from "../components/ListRequestsComponent";
import ListConnectedComponent from "../components/ListConnectedComponent";

const DashboardComponent = () => {
  const { currentUser, isLoading, logout } = useAuth();
  const [activePanel, setActivePanel] = useState("profile");
  const navigate = useNavigate();

  useEffect(() => {
    if (!isLoading && !currentUser) {
      navigate("/");
    }
  }, [isLoading, currentUser, navigate]);

  if (isLoading) {
    return <p className="text-white">Loading...</p>;
  }
  if (!currentUser) return null;
  return (
    <div className="dashboard w-full h-full flex bg-box">
      <aside className="h-full bg-white shadow-md flex flex-col justify-between w-1/4">
        <h4 className="text-base border-2 border-gray-200 flex p-4 items-center justify-start gap-4">
          Dashboard
        </h4>
        <div className="p-8 h-full flex flex-col justify-start gap-8">
          <nav>
            <ul className="flex flex-col gap-1">
              <li>
                <button
                  className="cell"
                  onClick={() => setActivePanel("profile")}
                >
                  Profile
                  <svg className="icon size-10">
                    <use xlinkHref="assets/icons.svg#star"></use>
                  </svg>
                </button>
              </li>
              <li>
                <button
                  className="cell"
                  onClick={() => setActivePanel("search")}
                >
                  Search
                  <svg className="icon size-10">
                    <use xlinkHref="assets/icons.svg#star"></use>
                  </svg>
                </button>
              </li>
              <li>
                <button
                  className="cell"
                  onClick={() => setActivePanel("settings")}
                >
                  Settings
                  <svg className="icon size-10">
                    <use xlinkHref="assets/icons.svg#star"></use>
                  </svg>
                </button>
              </li>
              <li>
                <button
                  className="cell"
                  onClick={() => setActivePanel("schedule")}
                >
                  Schedule
                  <svg className="icon size-10">
                    <use xlinkHref="assets/icons.svg#star"></use>
                  </svg>
                </button>
              </li>
              <li>
                <button
                  className="cell"
                  onClick={() => setActivePanel("messages")}
                >
                  Messages
                  <svg className="icon size-10">
                    <use xlinkHref="assets/icons.svg#star"></use>
                  </svg>
                </button>
              </li>
              <li>
                <button
                  className="cell"
                  onClick={() => setActivePanel("chats")}
                >
                  Chats
                  <svg className="icon size-10">
                    <use xlinkHref="assets/icons.svg#star"></use>
                  </svg>
                </button>
              </li>
              <li>
                <button
                  className="cell"
                  onClick={() => setActivePanel("requests")}
                >
                  Requests
                  <svg className="icon size-10">
                    <use xlinkHref="assets/icons.svg#star"></use>
                  </svg>
                </button>
              </li>
            </ul>
          </nav>
        </div>
        <UserAccount account={currentUser} onLogout={logout} />
      </aside>
      <div className="panel w-full overflow-y-auto">
        {activePanel === "search" && <ListUserComponent />}
        {activePanel === "profile" && <ProfileComponent />}
        {activePanel === "settings" && <SettingsComponent />}
        {activePanel === "requests" && <ListRequestsComponent />}
        {activePanel === "chats" && <ListConnectedComponent />}
      </div>
    </div>
  );
};

export default DashboardComponent;
