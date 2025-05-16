import "./App.css";
import ListUserComponent from "./components/user/ListUserComponent";
import SignInComponent from "./components/auth/SignInComponent";
import LoginComponent from "./components/auth/LoginComponent";
import { BrowserRouter, Routes, Route } from "react-router-dom";

function App() {
  return (
    <BrowserRouter>
      <main className="">
        <h1 className="text-blue-300"></h1>
        <Routes>
          <Route path="/" element={<LoginComponent />}></Route>
          <Route path="/users" element={<ListUserComponent />}></Route>
          <Route path="/signIn" element={<SignInComponent />}></Route>
        </Routes>
      </main>
    </BrowserRouter>
  );
}

export default App;
