import type { ReactElement } from "react";
import { Navigate } from "react-router-dom";
import { useAuth } from "../context/useAuth";

const PrivateRoute = ({ children }: { children: ReactElement }) => {
  const { isAuthenticated, isLoading } = useAuth();

  if (isLoading) return <p className="text-white">Loading...</p>;
  if (!isAuthenticated) return <Navigate to="/" replace />;

  return children;
};

export default PrivateRoute;
