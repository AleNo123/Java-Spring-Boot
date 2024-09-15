import React, { useState } from 'react';
import axios from 'axios';
import postRequest from '../PostRequest';

const ForgotPasswordRequest = () => {
    const [email, setEmail] = useState('');
    const [message, setMessage] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
         const response = await postRequest('public/user/forgotPassword', new URLSearchParams({
                    email,
         }));

         setMessage(response.message);
         setError(response.error);
    };

    return (
        <div>
            <h2>Forgot Password</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="email">Email:</label>
                    <input
                        type="email"
                        id="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </div>
                <button type="submit">Send Verification Email</button>
            </form>
            {message && (
                            <div style={{ color: error ? 'red' : 'green' }}>
                                {message}
                            </div>
                        )}
        </div>
    );
};

export default ForgotPasswordRequest;