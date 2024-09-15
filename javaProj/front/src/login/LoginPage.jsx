import React, { useState, useEffect } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import config from '../config.jsx';
import axios from 'axios';
import postRequest from '../PostRequest';

const LoginPage = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(false);
    const [message, setMessage] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        const response = await postRequest('public/user/login', new URLSearchParams({
                    username,
                    password,
                }));
        console.log(response)
        setMessage(response.message);
        setError(response.error);
        if(!response.error){
             navigate("/user/profile")
        }
    };

    return (
        <div>
            <h1>Please Log In</h1>
            {message && (
                  <div style={{ color: error ? 'red' : 'green' }}>
                       {message}
                  </div>
            )}
            <form onSubmit={handleSubmit}>
                <div>
                    <input
                        type="text"
                        name="username"
                        placeholder="Username"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <input
                        type="password"
                        name="password"
                        placeholder="Password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>

                <input type="submit" value="Log in" />
            </form>
            <Link to="/user/registration">Register</Link>
            {error && <Link to="/user/forgotPassword">Forgot Password</Link>}
        </div>
    );
};

export default LoginPage;