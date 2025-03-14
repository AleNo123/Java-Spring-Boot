import axios from 'axios';
import config from '../config';
import qs from 'qs';

const getCsrfToken = () => {
    const match = document.cookie.match(new RegExp('(^| )XSRF-TOKEN=([^;]+)'));
    return match ? match[2] : null;
};

export const getAuthorizedUser = async () => {
    try {
        const response = await axios.get(`${config.API_BASE_URL}private/user/profile`, { withCredentials: true ,
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
export const getFile = async (filePath) => {
  try {
    const response = await axios.get(`${config.API_BASE_URL}private/files/load`, {
      withCredentials: true ,
      params: { filePath },
      responseType: 'blob',
      headers: {
            'X-XSRF-TOKEN': getCsrfToken() // Добавляем CSRF-токен в заголовок
      }
    });
    console.log('Response data (Blob):', response);
    return URL.createObjectURL(response.data);
  } catch (error) {
    console.error('Error fetching file:', error);
    throw error;
  }
};
export const updateUser = async (userData) => {
    console.log(userData);
    try {
        const response = await axios.put(`${config.API_BASE_URL}private/user/updateUser`, userData, {
            withCredentials: true,
            headers: {
                'X-XSRF-TOKEN': getCsrfToken(),
                'Content-Type': 'application/x-www-form-urlencoded',
            }
        });
        console.log(response)
        if (response.status !== 200) {
            throw new Error('Не удалось обновить пользователя');
        }
    } catch (error) {
        console.error('Ошибка при обновлении пользователя:', error);
        throw error;
    }
};
