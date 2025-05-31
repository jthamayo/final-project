import { useForm, SubmitHandler } from "react-hook-form";
import { useState } from "react";
import { loginUser } from "../../services/UserService";

type LoginValues = {
  usernameOrEmail: string;
  password: string;
};

const LoginComponent = ({ onLogin }: { onLogin: () => void }) => {
  const { register, handleSubmit } = useForm<LoginValues>();
  const [error, setError] = useState("");
  const onSubmit: SubmitHandler<LoginValues> = (data) => {
    loginUser(data)
      .then(() => {
        onLogin();
      })
      .catch((error) => {
        console.error("Login failed:", error.message);
        setError("Invalid username or password");
      });
  };

  return (
    <>
      <h2 className="text-white font-title text-4xl text-center"> Login</h2>
      <div className=" my-5 w-120 h-100 bg-white rounded-4xl">
        <form
          onSubmit={handleSubmit(onSubmit)}
          className="flex flex-col justify-between h-full p-15"
        >
          <label htmlFor="usernameOrEmail" className="font-title text-xl">
            Username or Email
          </label>
          <input
            type="text"
            {...register("usernameOrEmail")}
            placeholder="Username or Email"
            className="bg-[#EDECF6] text-black w-full h-1/5 pl-5 rounded-xl"
          />
          <label htmlFor="password" className="font-title text-xl">
            Password
          </label>
          <input
            type="password"
            {...register("password")}
            placeholder="Password"
            className="bg-[#EDECF6] text-black w-full h-1/5 pl-5 rounded-xl"
          />
          {error && <p className="text-red-500">{error}</p>}
          <button
            type="submit"
            className="bg-base text-white text-center text-xl font-title w-full h-1/5 pl-5 rounded-xl mt-10"
          >
            Sign in
          </button>
        </form>
      </div>
    </>
  );
};
export default LoginComponent;
