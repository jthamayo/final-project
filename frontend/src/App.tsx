import "./App.css";
import { Routes, Route, Navigate } from "react-router-dom";
import SignupComponent from "./pages/auth/SignupComponent";
import LoginComponent from "./pages/auth/LoginComponent";
import NotFound from "./common/NotFound";
import PrivateRoute from "./common/PrivateRoute";
import ProfileComponent from "./components/user/ProfileComponent";
import DashboardComponent from "./pages/DashboardComponent";
import ChatsContainerComponent from "./components/chats/ChatsContainerComponent";
import ListConnectedComponent from "./components/ListConnectedComponent";
import ListRequestsComponent from "./components/ListRequestsComponent";
import { SettingsComponent } from "./components/SettingsComponent";
import ListUserComponent from "./components/ListUserComponent";

function App() {
  return (
    <main className="content w-full h-full flex items-center justify-center">
      <Routes>
        <Route path="/signup" element={<SignupComponent />}></Route>
        <Route path="/login" element={<LoginComponent />}></Route>
        <Route
          path="/"
          element={
            <PrivateRoute>
              <Navigate to="/dashboard" />
            </PrivateRoute>
          }
        />
        <Route
          path="/users/:username"
          element={
            <PrivateRoute>
              <ProfileComponent />
            </PrivateRoute>
          }
        ></Route>
        <Route
          path="dashboard/*"
          element={
            <PrivateRoute>
              <DashboardComponent />
            </PrivateRoute>
          }
        >
          <Route index element={<ProfileComponent />} />
          <Route path="profile" element={<ProfileComponent />} />
          <Route path="search" element={<ListUserComponent />} />
          <Route path="settings" element={<SettingsComponent />} />
          <Route path="requests" element={<ListRequestsComponent />} />
          <Route path="friends" element={<ListConnectedComponent />} />
          <Route path="chats" element={<ChatsContainerComponent />} />
          <Route path="*" element={<NotFound />} />
        </Route>
        <Route path="*" element={<NotFound />} />
      </Routes>
    </main>
  );
}

export default App;
