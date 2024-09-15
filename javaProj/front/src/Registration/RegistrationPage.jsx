import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import config from '../config';
import postRequest from '../PostRequest';

const RegistrationPage = () => {
    const [nickname, setNickname] = useState('');
    const [password, setPassword] = useState('');
    const [passwordCopy, setPasswordCopy] = useState('');
    const [emailAddress, setEmailAddress] = useState('');
    const [message, setMessage] = useState('');
    const [error, setError] = useState(false);
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
//         if (password !== passwordCopy) {
//             setMessage('Passwords do not match');
//             setError(true);
//             return;
//         }
        const response = await postRequest('public/user/registration', new URLSearchParams({
            nickname,
            password,
            passwordCopy,
            emailAddress,
        }));
        console.log(response)
        setMessage(response.message);
        setError(response.error);
//         if(!response.error){
//              navigate("/home")
//         }
    };

//     const handleSubmit = async (e) => {
//         e.preventDefault();
//         if (password !== passwordCopy) {
//             setMessage('Passwords do not match');
//             setError(true);
//             return;
//         }
//
//         try {
//             const csrfToken = getCsrfToken();
//             const response = await axios.post(`${config.API_BASE_URL}/registration`, {
//                 nickname,
//                 password,
//                 emailAddress
//             }, {
//                 headers: {
//                     'X-XSRF-TOKEN': csrfToken,
//                     'Content-Type': 'application/json',
//                 },
//                 withCredentials: true
//             });
//
//             if (response.data.error) {
//                 setMessage(response.data.message || 'Registration failed');
//                 setError(true);
//             } else {
//                 setMessage('Registration successful. Please check your email to confirm.');
//                 setError(false);
//                 // Optionally navigate to another page, e.g., the login page
//                 // navigate('/login');
//             }
//         } catch (err) {
//             setMessage('An error occurred');
//             setError(true);
//         }
//     };

    return (
        <div>
            <h2>User Registration Form</h2>
            {message && (
                <div style={{ color: error ? 'red' : 'green' }}>
                    {message}
                </div>
            )}
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="nickname">Nickname:</label>
                    <input
                        type="text"
                        id="nickname"
                        name="nickname"
                        value={nickname}
                        onChange={(e) => setNickname(e.target.value)}
                        required
                        minLength="4"
                        maxLength="25"
                    />
                </div>
                <div>
                    <label htmlFor="password">Password:</label>
                    <input
                        type="password"
                        id="password"
                        name="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                        minLength="8"
                        maxLength="30"
                    />
                </div>
                <div>
                    <label htmlFor="passwordCopy">Confirm Password:</label>
                    <input
                        type="password"
                        id="passwordCopy"
                        name="passwordCopy"
                        value={passwordCopy}
                        onChange={(e) => setPasswordCopy(e.target.value)}
                        required
                        minLength="8"
                        maxLength="30"
                    />
                </div>
                <div>
                    <label htmlFor="emailAddress">Email Address:</label>
                    <input
                        type="email"
                        id="emailAddress"
                        name="emailAddress"
                        value={emailAddress}
                        onChange={(e) => setEmailAddress(e.target.value)}
                        required
                    />
                </div>
                <button type="submit">Submit</button>
            </form>
        </div>
    );
};

export default RegistrationPage;