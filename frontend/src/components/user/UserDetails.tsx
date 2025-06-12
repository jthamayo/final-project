import { useState } from "react";
import { updateUserProfile, UserProfile } from "../../services/UserService";

export const UserDetails = ({ currentUser }: { currentUser: UserProfile }) => {
  const [edit, setEdit] = useState(false);
  const [formData, setFormData] = useState({
    firstName: currentUser.firstName,
    lastName: currentUser.lastName,
    username: currentUser.username,
    phoneNumber: currentUser.phoneNumber,
    email: currentUser.email,
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSave = async () => {
    try {
      const update = { ...currentUser, ...formData };
      await updateUserProfile(update);
      setEdit(false);
    } catch (err) {
      console.log("Update failed", err);
    }
  };
  return (
    <div className="bg-white min-w-[500px] xl:w-1/2 p-8 rounded-md flex justify-center">
      <div className="flex flex-col gap-8 w-full">
        <div className="flex gap-4 items-center justify-between">
          <h2 className="text-xl font-bold">User Details</h2>
          <div className="flex gap-2">
            {edit && (
              <button
                onClick={handleSave}
                className="bg-blue-600 text-white p-2 rounded"
              >
                Save Changes
              </button>
            )}
            <button
              onClick={() => setEdit(!edit)}
              className="bg-accent p-2 rounded text-white"
            >
              {edit ? "Cancel" : "Edit"}
            </button>
          </div>
        </div>

        {edit ? (
          <>
            <div className="flex gap-2">
              <div className="flex flex-col gap-4 items-start min-w-40">
                <p className="flex items-center p-4 rounded-md">First Name:</p>
                <p className="flex items-center p-4 rounded-md">Last Name:</p>
                <p className="flex items-center p-4 rounded-md">Username:</p>
                <p className="flex items-center p-4 rounded-md">Email:</p>
                <p className="flex items-center p-4 rounded-md">
                  Phone Number:
                </p>
              </div>
              <div className="flex flex-col items-start gap-4 w-full">
                <input
                  className="flex items-start bg-box py-4 px-8 rounded-md w-full"
                  name="firstName"
                  value={formData.firstName}
                  onChange={handleChange}
                  placeholder="First Name"
                />
                <input
                  className="flex items-start bg-box py-4 px-8 rounded-md w-full"
                  name="lastName"
                  value={formData.lastName}
                  onChange={handleChange}
                  placeholder="Last Name"
                />
                <input
                  className="flex items-start bg-box py-4 px-8 rounded-md w-full"
                  name="username"
                  value={formData.username}
                  onChange={handleChange}
                  placeholder="Username"
                />
                <input
                  className="flex items-start bg-box py-4 px-8 rounded-md w-full"
                  name="email"
                  value={formData.email}
                  onChange={handleChange}
                  placeholder="Email"
                />
                <input
                  className="flex items-start bg-box py-4 px-8 rounded-md w-full"
                  name="phoneNumber"
                  value={formData.phoneNumber}
                  onChange={handleChange}
                  placeholder="Phone Number"
                />
              </div>
            </div>
          </>
        ) : (
          <>
            <div className="flex gap-2">
              <div className="flex flex-col gap-4 items-start min-w-40">
                <p className="flex items-center p-4 rounded-md">First Name:</p>
                <p className="flex items-center p-4 rounded-md">Last Name:</p>
                <p className="flex items-center p-4 rounded-md">Username:</p>
                <p className="flex items-center p-4 rounded-md">Email:</p>
                <p className="flex items-center p-4 rounded-md">
                  Phone Number:
                </p>
              </div>
              <div className="flex flex-col items-start gap-4 w-full">
                <p className="flex items-start bg-box py-4 px-8 rounded-md w-full">
                  {currentUser.firstName}
                </p>
                <p className="flex items-start bg-box py-4 px-8 rounded-md w-full">
                  {" "}
                  {currentUser.lastName}
                </p>
                <p className="flex items-start bg-box py-4 px-8 rounded-md w-full">
                  {currentUser.username}
                </p>
                <p className="flex items-start bg-box py-4 px-8 rounded-md w-full">
                  {currentUser.email}
                </p>
                <p className="flex items-start bg-box py-4 px-8 rounded-md w-full">
                  {currentUser.phoneNumber}
                </p>
              </div>
            </div>
          </>
        )}
      </div>
    </div>
  );
};
