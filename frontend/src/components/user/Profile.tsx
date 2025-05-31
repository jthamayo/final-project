import { User } from "../../services/UserService";

interface ProfileProps {
  currentUser: User | null;
}

const Profile = ({ currentUser }: ProfileProps) => {
  if (!currentUser) return <p className="text-white">Loading user...</p>;
  return (
    <>
      <h1 className="text-white">Hello, {currentUser.username}</h1>
    </>
  );
};

export default Profile;
