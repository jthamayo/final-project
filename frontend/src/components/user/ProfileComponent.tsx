import { useState, useEffect } from "react";
import { getProfileInformation } from "../../services/UserService";
import { UserProfile } from "../../services/UserService";
import { VerificationProcess } from "./VerificationProcess";
import Loading from "../../common/Loading";
import { UserDetails } from "./UserDetails";

const ProfileComponent = () => {
  const [user, setUser] = useState<UserProfile | null>(null);

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

  if (!user) {
    return <Loading />;
  }

  return (
    <section className="m-4">
      <div className="flex max-sm:flex-col w-full justify-between gap-4">
        <div className="w-full bg-white p-8 rounded-md">
          <h2 className="text-black">Hello, {user.username}</h2>{" "}
        </div>
        {!user.isVerified && <VerificationProcess />}
      </div>
      {!user.isVerified && (
        <div className="flex py-4 gap-4">
          {user.jobs.length === 0 && (
            <button className="h-40 rounded-3xl bg-accent aspect-square">
              {" "}
              + Add a job
            </button>
          )}
          {!user.vehicle && (
            <button className="h-40 rounded-3xl bg-accent aspect-square">
              {" "}
              + Add a Vehicle
            </button>
          )}
          {!user.address && (
            <button className="h-40 rounded-3xl bg-accent aspect-square">
              {" "}
              + Add an Address
            </button>
          )}
        </div>
      )}
      <UserDetails currentUser={user}/>
    </section>
  );
};

export default ProfileComponent;
