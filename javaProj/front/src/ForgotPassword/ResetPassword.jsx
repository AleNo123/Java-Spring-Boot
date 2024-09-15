import React, { useState } from 'react';
import axios from 'axios';
import { useSearchParams } from 'react-router-dom';
import postRequest from '../PostRequest';

const ResetPassword = () => {
    const [password, setPassword] = useState('');
    const [passwordCopy, setPasswordCopy] = useState('');
    const [message, setMessage] = useState('');
    const [error, setError] = useState('');
    const [searchParams] = useSearchParams();

    const token = searchParams.get('token');

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (password !== passwordCopy) {
            setError('Passwords do not match');
            return;
        }
        const response = await postRequest('/public/user/resetPassword', new URLSearchParams({
                            password,
                            passwordCopy,
                            token
        }));
       setMessage(response.message);
                setError(response.error);
    };

    return (
        <div>
            <h2>Reset Password</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="password">New Password:</label>
                    <input
                        type="password"
                        id="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="passwordCopy">Confirm Password:</label>
                    <input
                        type="password"
                        id="passwordCopy"
                        value={passwordCopy}
                        onChange={(e) => setPasswordCopy(e.target.value)}
                        required
                    />
                </div>
                <button type="submit">Change Password</button>
            </form>
            {message && (
                                        <div style={{ color: error ? 'red' : 'green' }}>
                                            {message}
                                        </div>
                                    )}
        </div>
    );
};

export default ResetPassword;