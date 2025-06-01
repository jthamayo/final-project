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
    return <p className="text-white">Loading user...</p>;
  }
  if (!currentUser) return null;
  return (
    <>
      <h1 className="text-white">Hello, {currentUser.username}</h1>
    </>
  );
};

export default ProfileComponent;
