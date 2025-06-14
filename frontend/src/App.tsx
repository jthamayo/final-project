import "./App.css";
import { Routes, Route } from "react-router-dom";

import SignupComponent from "./pages/auth/SignupComponent";
import LoginComponent from "./pages/auth/LoginComponent";
import NotFound from "./common/NotFound";
import PrivateRoute from "./common/PrivateRoute";
import ProfileComponent from "./components/user/ProfileComponent";
import DashboardComponent from "./pages/DashboardComponent";

function App() {
  return (
    <main className="content w-full h-full flex items-center justify-center">
      <Routes>
        <Route
          path="/"
          element={
            <PrivateRoute>
              <DashboardComponent />
            </PrivateRoute>
          }
        ></Route>
        <Route path="/signup" element={<SignupComponent />}></Route>
        <Route path="/login" element={<LoginComponent />}></Route>
        <Route
          path="/users/:username"
          element={
            <PrivateRoute>
              <ProfileComponent />
            </PrivateRoute>
          }
        ></Route>
        <Route path="*" element={<NotFound />} />
      </Routes>
    </main>
  );
}

export default App;
