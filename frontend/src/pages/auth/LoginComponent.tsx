import { useForm, SubmitHandler } from "react-hook-form";
import { useState } from "react";
import { useAuth } from "../../context/useAuth";
import { Login } from "../../services/UserService";
import { useNavigate } from "react-router-dom";

const LoginComponent = () => {
  const { register, handleSubmit } = useForm<Login>();
  const [error, setError] = useState("");
  const { login } = useAuth();
  const navigate = useNavigate();

  const onSubmit: SubmitHandler<Login> = async (data: {
    usernameOrEmail: string;
    password: string;
  }) => {
    try {
      await login(data.usernameOrEmail, data.password);
    } catch {
      setError("Invalid username or password");
    }
  };

  return (
    <div className="flex flex-col">
      <h2 className="text-white font-title text-4xl text-center"> Login</h2>
      <div className=" my-5 w-120 bg-white rounded-4xl">
        <form
          onSubmit={handleSubmit(onSubmit)}
          className="flex flex-col justify-between h-full p-15 gap-4"
        >
          <label htmlFor="usernameOrEmail" className="font-title text-xl">
            Username or Email
          </label>
          <input
            type="text"
            {...register("usernameOrEmail")}
            placeholder="Username or Email"
            className="bg-[#EDECF6] text-black w-full h-1/5 pl-5 rounded-xl box"
          />
          <label htmlFor="password" className="font-title text-xl">
            Password
          </label>
          <input
            type="password"
            {...register("password")}
            placeholder="Password"
            className="bg-[#EDECF6] text-black w-full h-1/5 pl-5 rounded-xl box"
          />
          {error && <p className="text-red-500">{error}</p>}
          <button
            type="submit"
            className="bg-base text-white text-center text-xl font-title w-full h-1/5 rounded-xl mt-10 box p-0"
          >
            Sign in
          </button>
          <a
            className="text-center hover:underline decoration-1"
            onClick={() => navigate("/signup")}
          >
            Or Sign up
          </a>
        </form>
      </div>
    </div>
  );
};
export default LoginComponent;
