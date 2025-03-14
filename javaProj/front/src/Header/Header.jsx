import React, { useEffect, useState } from 'react';
import { getAuthorizedUser, getFile } from '../services/UserService';
import axios from 'axios';
import config from '../config';

const Header = () => {
    const [user, setUser] = useState(null);

    function delay(ms) {
      return new Promise(resolve => setTimeout(resolve, ms));
    }
    const getCsrfToken = () => {
        const match = document.cookie.match(new RegExp('(^| )XSRF-TOKEN=([^;]+)'));
        return match ? match[2] : null;
    };

    useEffect(() => {
        const fetchUser = async () => {
            try {
                    const response = await axios.get(`${config.API_BASE_URL}private/user/logout`, { withCredentials: true ,
                        headers: {
                             'X-XSRF-TOKEN': getCsrfToken() // Добавляем CSRF-токен в заголовок
                        }
                    });
                    console.log(response)
                    return response.data;
                } catch (error) {
                    console.error('Error fetching user data:', error);
                    throw error;
                }
        };

        fetchUser();
    }, []);


    return (
        <h1>Please Log In</h1>
    );
};
export default Header;
