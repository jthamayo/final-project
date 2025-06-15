import { useState, useEffect } from "react";
import { getProfileInformation } from "../../services/UserService";
import {
  UserProfile,
  uploadProfilePicture,
  ProfilePictureProps,
} from "../../services/UserService";
import { VerificationProcess } from "./VerificationProcess";
import Loading from "../../common/Loading";
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
    <section className="p-4 flex flex-col w-full justify-between gap-4">
        <div className="flex relative overflow-hidden h-[300px]">
          <div className="h-1/2 w-full absolute bg-base rounded-lg">
          </div>
          <div className="z-10 h-2/3 m-auto flex justify-center items-center">
            <div className="size-50 bg-white p-2 aspect-square rounded-lg relative">
              {isUploading ? (
                <p>Uploading...</p>
              ) : profilePic ? (
                <UserAvatar url={profilePic.url} />
              ) : (
                <UserAvatar url={user.profilePictureUrl} />
              )}
              <label
                htmlFor="profile_pic"
                className="size-12 absolute -top-2 -right-2 cursor-pointe rounded-full bg-base border-3 border-white"
              >
                <input
                  type="file"
                  id="profile_pic"
                  onChange={handlePictureChange}
                  className="size-10 opacity-0"
                  accept=".jpg,.png,.jpeg"
                />
                <svg className="size-9 absolute top-1/2 left-1/2 -translate-1/2 p-1">
                  <use xlinkHref="assets/icons.svg#pencil"></use>
                </svg>
              </label>
            </div>
            <div className="absolute -bottom-4 left-1/2 -translate-1/2">
              <h2 className="text-base text-center font-bold">Hello, {user.username}</h2>
            </div>
          </div>
        </div>
        {!user.isVerified && (
          <>
            <VerificationProcess />
            <div className="grid grid-flow-col grid-rows-2 md:grid-rows-1 gap-4 py-4">
              {user.jobs.length === 0 && (
                <button className="square-button aspect-auto">
                  <svg className="plus-icon size-1/3 text-muted">
                    <use xlinkHref="assets/icons.svg#calendar"></use>
                  </svg>
                  <p> Add a job</p>
                </button>
              )}
              {!user.vehicle && (
                <button className="square-button aspect-auto">
                  <svg className="plus-icon size-1/3 text-muted">
                    <use xlinkHref="assets/icons.svg#car"></use>
                  </svg>
                  <p> Add a vehicle</p>
                </button>
              )}
              {!user.address && (
                <button className="square-button aspect-auto">
                  <svg className="plus-icon size-1/3 text-muted">
                    <use xlinkHref="assets/icons.svg#location"></use>
                  </svg>
                  <p>Add an address</p>
                </button>
              )}
              {!user.children && (
                <button className="square-button aspect-auto">
                  <svg className="plus-icon size-1/3 text-muted">
                    <use xlinkHref="assets/icons.svg#users"></use>
                  </svg>
                  <p>Add family</p>
                </button>
              )}
            </div>
          </>
        )}
    </section>
  );
};

export default ProfileComponent;
