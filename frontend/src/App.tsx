import "./App.css";
import { Routes, Route } from "react-router-dom";

import ListUserComponent from "./components/user/ListUserComponent";
import SignupComponent from "./components/auth/SignupComponent";
import LoginComponent from "./components/auth/LoginComponent";
import NotFound from "./common/NotFound";
import PrivateRoute from "./common/PrivateRoute";
import ProfileComponent from "./components/user/ProfileComponent";
import DashboardComponent from "./components/user/DashboardComponent";

function App() {
  return (
    <main className="content w-full h-full flex items-center justify-center">
      <Routes>
        <Route path="/" element={<LoginComponent />}></Route>
        <Route path="/users" element={<ListUserComponent />}></Route>
        <Route path="/signup" element={<SignupComponent />}></Route>
        <Route
          path="/users/:username"
          element={
            <PrivateRoute>
              <ProfileComponent />
            </PrivateRoute>
          }
        ></Route>
        <Route
          path="/dashboard"
          element={
            <PrivateRoute>
              <DashboardComponent />
            </PrivateRoute>
          }
        ></Route>
        <Route path="*" element={<NotFound />} />
      </Routes>
    </main>
  );
}

export default App;
