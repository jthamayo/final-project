import { User } from "../../services/UserService";

interface ProfileProps {
  currentUser: User | null;
}

const Profile = ({ currentUser }: ProfileProps) => {
  if (!currentUser) return <p>Loading user...</p>;
  return (
    <>
      <h1>Hello, {currentUser.username}</h1>
    </>
  );
};

export default Profile;
