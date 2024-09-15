import axios from 'axios';
import config from './config'; // Импортируйте ваш конфигурационный файл

const getCsrfToken = () => {
    const match = document.cookie.match(new RegExp('(^| )XSRF-TOKEN=([^;]+)'));
    return match ? match[2] : null;
};

const postRequest = async (url, data, additionalHeaders = {}) => {
    try {
        const csrfToken = getCsrfToken();
        const response = await axios.post(`${config.API_BASE_URL}${url}`, data, {
            headers: {
                'X-XSRF-TOKEN': csrfToken,
                'Content-Type': 'application/x-www-form-urlencoded',
                ...additionalHeaders,
            },
            withCredentials: true,
        });
        console.log(response)
        return response.data;
    } catch (error) {
            if (error.response) {
                return {
                    error: true,
                    message: error.response.data.message,
                    status: error.response.status,
                };
            } else {
                return {
                    error: true,
                    message: error.message,
                };
            }
    }
};

export default postRequest;