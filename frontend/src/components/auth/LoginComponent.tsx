import { useForm, SubmitHandler } from "react-hook-form";

type LoginValues = {
  usernameOrEmail: string;
  password: string;
};

const LoginComponent = () => {
  const { register, handleSubmit } = useForm<LoginValues>();
  const onSubmit: SubmitHandler<LoginValues> = (data) => console.log(data);
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
