import { useEffect, useState, ReactNode } from "react";
import { useNavigate } from "react-router-dom";
import { ACCESS_TOKEN } from "../constants";
import { getCurrentUser, loginUser, User } from "../services/UserService";
import { AuthContext } from "./AuthContext";

export const AuthProvider = ({ children }: { children: ReactNode }) => {
  const [currentUser, setCurrentUser] = useState<User | null>(null);
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [isLoading, setIsLoading] = useState(true);
  const navigate = useNavigate();

  const loadCurrentUser = async () => {
    const token = localStorage.getItem(ACCESS_TOKEN);
    if (!token) {
      setIsLoading(false);
      return;
    }
    try {
      const user = await getCurrentUser();
      setCurrentUser(user);
      setIsAuthenticated(true);
    } catch {
      setIsAuthenticated(false);
      setCurrentUser(null);
    } finally {
      setIsLoading(false);
    }
  };

  const login = async (usernameOrEmail: string, password: string) => {
    try {
      const res = await loginUser({ usernameOrEmail, password });
      localStorage.setItem(ACCESS_TOKEN, res.accessToken);
      await loadCurrentUser();
      navigate(`/`);
    } catch (err) {
      console.error("Login failed", err);
      throw err;
    }
  };

  const logout = () => {
    localStorage.removeItem(ACCESS_TOKEN);
    setCurrentUser(null);
    setIsAuthenticated(false);
    navigate("/");
  };

  useEffect(() => {
    loadCurrentUser();
  }, []);

  return (
    <AuthContext.Provider
      value={{ currentUser, isAuthenticated, isLoading, login, logout }}
    >
      {children}
    </AuthContext.Provider>
  );
};
