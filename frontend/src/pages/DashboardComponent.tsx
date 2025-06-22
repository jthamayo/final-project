import { useEffect} from "react";
import { useAuth } from "../context/useAuth";
import { useNavigate, Outlet } from "react-router-dom";
import SidebarComponent from "../components/SidebarComponent";

const DashboardComponent = () => {
  const { currentUser, isLoading, logout } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (!isLoading && !currentUser) {
      navigate("/login");
    }
  }, [isLoading, currentUser, navigate]);

  if (isLoading) {
    return <p className="text-white">Loading...</p>;
  }
  if (!currentUser) return null;

  return (
   <div className="dashboard w-full h-full flex bg-box">
      <SidebarComponent onLogout={logout} currentUser={currentUser} />
      <div className="panel w-full overflow-y-auto max-sm:mt-15">
        <Outlet />
      </div>
    </div>
  );
};

export default DashboardComponent;
