import { useEffect } from "react";
import { useAuth } from "../../context/useAuth";
import { useNavigate } from "react-router-dom";

const ProfileComponent = () => {
  const { currentUser, isLoading } = useAuth();
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
              <li className="list">Profile</li>
              <li className="list">Settings</li>
              <li className="list">Schedule</li>
              <li className="list">Messages</li>
            </ul>
          </nav>
        </div>
        <div className="details border-2 border-gray-200 flex p-4 items-center justify-start gap-4">
          <div className="avatar h-full aspect-square bg-light rounded-md"></div>
          <div>
            <div className="text-xl">{currentUser.username}</div>
            <div className="text-gray-400">{currentUser.email}</div>
          </div>
          <button className="size-5 bg-base rounded-full"></button>
        </div>
      </aside>
      <section className="h-full w-4/5 p-8">
        <hgroup className="flex items-center justify-start gap-2">
          <h1 className="">Hello, {currentUser.username}</h1>
          <h2
            className={
              currentUser.isVerified ? "text-green-400" : "text-red-400"
            }
          >
            · {currentUser.isVerified ? "Verified" : "Not Verified"}
          </h2>{" "}
        </hgroup>
        <ul className="flex flex-col gap-1 h-full mt-8">
          {!currentUser.jobId && <li className="card">Add a job +</li>}
          {!currentUser.addressId && <li className="card">Add an address +</li>}
          {!currentUser.childrenId && <li className="card">Add a child +</li>}
          {!currentUser.vehicleId && <li className="card">Add a vehicle +</li>}
        </ul>
      </section>
    </div>
  );
};

export default ProfileComponent;
