import { useState, useEffect } from "react";
import { getProfileInformation } from "../../services/UserService";
import { User } from "../../services/UserService";

const ProfileComponent = ({ username }: { username: string }) => {
  const [user, setUser] = useState<User | null>(null);

  useEffect(() => {
    const fetchProfile = async () => {
      try {
        const data = await getProfileInformation();
        setUser(data);
      } catch (err) {
        console.error("Failed to fetch profile", err);
      }
    };
    fetchProfile();
  }, []);
  return (
    <>
      {user ? (
        <h1 className="text-white">Hello, {user.username}</h1>
      ) : (
        <p className="text-white">Loading profile...</p>
      )}
    </>
  );
};

export default ProfileComponent;
