import { useForm, SubmitHandler } from "react-hook-form";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Signup, signupUser } from "../../services/UserService";
import {
  EMAIL_MAX_LENGTH,
  FIRSTNAME_MAX_LENGTH,
  FIRSTNAME_MIN_LENGTH,
  LASTNAME_MAX_LENGTH,
  LASTNAME_MIN_LENGTH,
  USERNAME_MIN_LENGTH,
  USERNAME_MAX_LENGTH,
  PASSWORD_MIN_LENGTH,
  PASSWORD_MAX_LENGTH,
} from "../../constants";

const SignupComponent = () => {
  const [error, setError] = useState("");
  const navigate = useNavigate();
  const {
    register,
    handleSubmit,
    watch,
    formState: { errors, isSubmitting },
  } = useForm<FormValues>();

  const watchedPassword = watch("password", "");

  interface FormValues extends Signup {
    confirmPassword: string;
    termsAccepted: boolean;
  }

  const onSubmit: SubmitHandler<FormValues> = async (data) => {
    const { firstName, lastName, username, email, phoneNumber, password } =
      data;
    try {
      await signupUser({
        firstName,
        lastName,
        username,
        email,
        phoneNumber,
        password,
      });
      navigate("/login");
    } catch {
      setError(
        "Failed to sign up. Check your data and fill all the required fields"
      );
    }
  };

  return (
    <div className="flex flex-col w-full px-8 h-full overflow-y-auto">
      <h2 className="mt-8 text-white font-title text-4xl text-center">
        Sign up
      </h2>
      <div className="w-full max-w-[1000px] m-auto bg-white rounded-4xl p-8 sm:p-16 relative">
        <form
          onSubmit={handleSubmit(onSubmit)}
          className="flex max-sm:flex-col justify-between gap-4 sm:gap-8"
          noValidate
        >
          <fieldset className="sm:w-1/2 flex flex-col gap-4 justify-evenly">
            <label htmlFor="firstName" className="font-title text-xl field">
              First Name
              {errors.firstName && (
                <p className="error text-sm">{errors.firstName.message}</p>
              )}
            </label>
            <input
              id="firstName"
              type="text"
              {...register("firstName", {
                required: "First name is required",
                minLength: {
                  value: FIRSTNAME_MIN_LENGTH,
                  message: "must be at least 2 characters long",
                },
                maxLength: {
                  value: FIRSTNAME_MAX_LENGTH,
                  message: "must be under 20 characters",
                },
              })}
              placeholder="First Name"
              className="box"
            />
            <label htmlFor="lastName" className="font-title text-xl field">
              Last Name
              {errors.lastName && (
                <p className="text-sm error">{errors.lastName.message}</p>
              )}
            </label>
            <input
              id="lastName"
              type="text"
              {...register("lastName", {
                required: "Last name is required",
                minLength: {
                  value: LASTNAME_MIN_LENGTH,
                  message: "must be at least 2 characters long",
                },
                maxLength: {
                  value: LASTNAME_MAX_LENGTH,
                  message: "must be under 20 characters",
                },
              })}
              placeholder="Last Name"
              className="box"
            />
            <label htmlFor="email" className="font-title text-xl field">
              Email
              {errors.email && (
                <p className="text-sm error">{errors.email.message}</p>
              )}
            </label>
            <input
              id="email"
              type="email"
              {...register("email", {
                required: "Email is required",
                pattern: {
                  value: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
                  message: "Enter a valid email",
                },
                maxLength: {
                  value: EMAIL_MAX_LENGTH,
                  message: "must be under 30 characters",
                },
              })}
              placeholder="Email"
              className="box"
            />

            <label htmlFor="phoneNumber" className="font-title text-xl field">
              Phone Number
              {errors.phoneNumber && (
                <p className="text-sm error">{errors.phoneNumber.message}</p>
              )}
            </label>
            <input
              id="phoneNumber"
              type="tel"
              className="box"
              placeholder="Phone Number"
              {...register("phoneNumber", {
                required: "Phone number is required",
                pattern: {
                  value: /^[0-9]{9}$/,
                  message: "Enter a valid phone number",
                },
              })}
            />
          </fieldset>
          <fieldset className="sm:w-1/2 flex flex-col gap-4 justify-evenly">
            <label htmlFor="username" className="font-title text-xl field">
              Username
              {errors.username && (
                <p className="text-sm error">{errors.username.message}</p>
              )}
            </label>
            <input
              id="username"
              type="text"
              {...register("username", {
                required: "username is required",
                minLength: {
                  value: USERNAME_MIN_LENGTH,
                  message: "must be at least 3 characters long",
                },
                maxLength: {
                  value: USERNAME_MAX_LENGTH,
                  message: "must be under 15 characters",
                },
              })}
              placeholder="Username"
              className="box"
            />

            <label htmlFor="password" className="font-title text-xl field">
              Password
              {errors.password && (
                <p className="text-sm error">{errors.password.message}</p>
              )}
            </label>
            <input
              id="password"
              type="password"
              {...register("password", {
                required: "password is required",
                minLength: {
                  value: PASSWORD_MIN_LENGTH,
                  message: "must be at least 6 characters long",
                },
                maxLength: {
                  value: PASSWORD_MAX_LENGTH,
                  message: "must be under 20 characters",
                },
              })}
              placeholder="Password"
              className="box"
            />

            <label htmlFor="ConfirmPassword" className=" text-xl field">
              Repeat Password
              {errors.confirmPassword && (
                <p className="text-sm error">
                  {errors.confirmPassword.message}
                </p>
              )}
            </label>
            <input
              id="confirmPassword"
              type="password"
              {...register("confirmPassword", {
                required: "Please confirm your password",
                validate: (value) =>
                  value === watchedPassword || "Passwords do not match",
              })}
              placeholder="Repeat Password"
              className="box"
            />

            <div className="flex gap-2 items-center box p-0 bg-transparent">
              <input
                className="h-4 w-4"
                id="termsAccepted"
                type="checkbox"
                {...register("termsAccepted", {
                  required: "You must accept the terms and conditions",
                })}
              />
              <label htmlFor="terms" className="field m-0 w-full">
                I accept the terms and conditions
                {errors.termsAccepted && (
                  <p className="text-sm error">
                    {errors.termsAccepted.message}
                  </p>
                )}
              </label>
            </div>

            <button
              type="submit"
              disabled={isSubmitting}
              className="box bg-base text-center text-white p-0 text-xl "
            >
              {isSubmitting ? "Signing up..." : "Sign up"}{" "}
            </button>
            {error && (
              <p className="error bottom-0 left-1/2 -translate-x-1/2">
                *{error}
              </p>
            )}
          </fieldset>
        </form>
      </div>
    </div>
  );
};
export default SignupComponent;
