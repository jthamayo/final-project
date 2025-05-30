import "./App.css";
import { useEffect, useState } from "react";
import { Routes, Route, useNavigate } from "react-router-dom";
import { User, getCurrentUser } from "./services/UserService.ts";
import { ACCESS_TOKEN } from "./constants/index.ts";

import ListUserComponent from "./components/user/ListUserComponent";
import Profile from "./components/user/Profile";
import SignupComponent from "./components/auth/SignupComponent";
import LoginComponent from "./components/auth/LoginComponent";
import Loading from "./common/Loading";
import NotFound from "./common/NotFound";

function App() {
  const [currentUser, setCurrentUser] = useState<User | null>(null);
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [isLoading, setIsLoading] = useState(true);

  const navigate = useNavigate();

  useEffect(() => {
    loadCurrentUser();
  }, []);

  const loadCurrentUser = () => {
    getCurrentUser()
      .then((user) => {
        setCurrentUser(user);
        setIsAuthenticated(true);
        setIsLoading(false);
      })
      .catch(() => {
        setIsLoading(false);
      });
  };
  const handleLogin = () => {
    loadCurrentUser();
    navigate("/");
  };
  const handleLogout = (redirectTo = "/") => {
    localStorage.removeItem(ACCESS_TOKEN);
    setCurrentUser(null);
    setIsAuthenticated(false);
    navigate(redirectTo);
  };

  if (isLoading) {
    return <Loading />;
  }

  return (
    <main className="content">
      <h1 className="text-blue-300"></h1>
      <Routes>
        <Route
          path="/"
          element={
            <LoginComponent onLogin={handleLogin} onLogout={handleLogout} />
          }
        ></Route>
        <Route path="/users" element={<ListUserComponent />}></Route>
        <Route path="/signup" element={<SignupComponent />}></Route>
        <Route
          path="/users/:username"
          element={<Profile currentUser={currentUser} />}
        ></Route>
        <Route path="*" element={<NotFound />} />
      </Routes>
    </main>
  );
}

export default App;
