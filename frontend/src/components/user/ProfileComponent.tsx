import { useState, useEffect } from "react";
import { getProfileInformation } from "../../services/UserService";
import {
  UserProfile,
  uploadProfilePicture,
  ProfilePictureProps,
} from "../../services/UserService";
import { VerificationProcess } from "./VerificationProcess";
import Loading from "../../common/Loading";
import { UserDetails } from "./UserDetails";
import UserAvatar from "./UserAvatar";

const ProfileComponent = () => {
  const [user, setUser] = useState<UserProfile | null>(null);
  const [profilePic, setProfilePic] = useState<ProfilePictureProps | null>(
    null
  );
  const [isUploading, setIsUploading] = useState(false);

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

  const handlePictureChange = async (
    e: React.ChangeEvent<HTMLInputElement>
  ) => {
    const file = e.target.files?.[0];
    if (!file) {
      return;
    }
    setIsUploading(true);
    try {
      const res = await uploadProfilePicture(file);
      setProfilePic(res);
    } catch (err) {
      console.error("Upload failed", err);
    } finally {
      setIsUploading(false);
    }
  };

  return (
    <section className="m-4">
      <div className="flex max-sm:flex-col w-full justify-between gap-4">
        <div className="w-full bg-white p-8 rounded-md flex gap-4">
          <div className="size-30 aspect-square flex items-center justify-center relative">
            {isUploading ? (
              <p>Uploading...</p>
            ) : profilePic ? (
              <UserAvatar url={profilePic.url} />
            ) : (
              <UserAvatar url={user.profilePictureUrl} />
            )}
            <label
              htmlFor="profile_pic"
              className="size-10 absolute -bottom-2 -right-2 cursor-pointer border-base rounded-full bg-white shadow-amber-600"
            >
              <input
                type="file"
                id="profile_pic"
                onChange={handlePictureChange}
                className="size-10 opacity-0"
                accept=".jpg,.png,.jpeg"
              />
              <svg className="size-full absolute top-0 right-0 p-1">
                <use xlinkHref="assets/icons.svg#pencil"></use>
              </svg>
            </label>
          </div>

          <h2 className="text-black">Hello, {user.username}</h2>
        </div>
        {!user.isVerified && <VerificationProcess />}
      </div>
      {!user.isVerified && (
        <div className="flex py-4 gap-4">
          {user.jobs.length === 0 && (
            <button className="square-button">
              <svg className="plus-icon size-1/3 text-muted">
                <use xlinkHref="assets/icons.svg#users"></use>
              </svg>
              <p> Add a job</p>
            </button>
          )}
          {!user.vehicle && (
            <button className="square-button">
              <svg className="plus-icon size-1/3 text-muted">
                <use xlinkHref="assets/icons.svg#car"></use>
              </svg>
              <p> Add a Vehicle</p>
            </button>
          )}
          {!user.address && (
            <button className="square-button">
              <svg className="plus-icon size-1/3 text-muted">
                <use xlinkHref="assets/icons.svg#location"></use>
              </svg>
              <p>Add an Address</p>
            </button>
          )}
        </div>
      )}
      <UserDetails currentUser={user} />
    </section>
  );
};

export default ProfileComponent;
