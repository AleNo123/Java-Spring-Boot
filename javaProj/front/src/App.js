import './App.css';
import { BrowserRouter as Router, Route,  Routes } from 'react-router-dom';
import LoginPage from './login/LoginPage.jsx';
import RegistrationPage from './Registration/RegistrationPage.jsx';
import ForgotPasswordRequest from './ForgotPassword/ForgotPasswordRequest.jsx';
import ResetPassword from './ForgotPassword/ResetPassword.jsx';
import UserProfile from './UserProfile/UserProfile.jsx';
import Header from './Header/Header.jsx';
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  return (
        <Router>
             <Routes>
                <Route path="/login" element={<LoginPage />} />
                <Route path="/user/registration" element={<RegistrationPage />} />
                <Route path="/user/forgotPassword" element={<ForgotPasswordRequest />} />
                <Route path="/user/resetPassword" element={<ResetPassword />} />
                <Route path="/user/profile" element={<UserProfile />} />
                <Route path="/logout" element={<Header />} />
             </Routes>
        </Router>
  );
}

export default App;
