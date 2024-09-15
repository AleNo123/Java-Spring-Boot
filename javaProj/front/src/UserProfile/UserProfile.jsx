import React, { useEffect, useState } from 'react';
import { getAuthorizedUser, getFile, updateUser } from '../services/UserService';
import { Modal, Button, Form } from 'react-bootstrap';


const UserProfile = () => {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [avatarUrl, setAvatarUrl] = useState('');
    const [avatar, setAvatar] = useState(null);
    const [showModal, setShowModal] = useState(false);
    const [formData, setFormData] = useState({
            nickname: '',
            name: '',
            surname: '',
    });


    function delay(ms) {
      return new Promise(resolve => setTimeout(resolve, ms));
    }

    useEffect(() => {
        const fetchUser = async () => {
            try {
                 // await delay(2000); // Задержка 2000 миллисекунд (2 секунды)
                const data = await getAuthorizedUser();

                setUser(data);
                    setFormData({
                    nickname: data.nickname,
                    name: data.name,
                    surname: data.surname,
                });
                setLoading(false);
                 // await delay(2000); // Задержка 2000 миллисекунд (2 секунды)
                const avatar = await getFile(data.pathToUserIcon);
                setAvatarUrl(avatar);

            } catch (err) {
                setError(err);
                setLoading(false);
            }
        };

        fetchUser();
    }, []);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error.message}</div>;
    }
    const handleChange = (e) => {
            const { name, value } = e.target;
            console.log(e.target);
            setFormData({ ...formData, [name]: value });
    };
     const handleSubmit = async (e) => {
            e.preventDefault();
            try {
                await updateUser(formData);
                setShowModal(false);
            } catch (err) {
                setError(err);
            }
        };
    const handleAvatarChange = (e) => {
        setAvatar(e.target.files[0]);
    };
    return (
        <div className="container">
            <h1>User Details</h1>
            {user && (
                <div className="card">
                    <div className="card-body">
                        <h5 className="card-title">User Information</h5>
                        <p className="card-text"><strong>User ID:</strong> {user.userId}</p>
                        <p className="card-text"><strong>Nickname:</strong> {user.nickname}</p>
                        <p className="card-text"><strong>Name:</strong> {user.name}</p>
                        <p className="card-text"><strong>Surname:</strong> {user.surname}</p>
                        <img src={avatarUrl} alt="User Avatar" style={{ width: 150, height: 150 }} />
                        <h5 className="card-title">Courses</h5>
                        <ul>
                            {user.courses && user.courses.map((course, index) => (
                                <li key={index}>{course.title}</li>
                            ))}
                        </ul>
                        <Button variant="secondary" onClick={() => setShowModal(true)}>Редактировать</Button>
                        <Modal show={showModal} onHide={() => setShowModal(false)}>
                            <Modal.Header closeButton>
                                <Modal.Title>Редактировать данные пользователя</Modal.Title>
                            </Modal.Header>
                            <Modal.Body>
                                <Form onSubmit={handleSubmit}>
                                    <Form.Group controlId="name">
                                        <Form.Label>Имя</Form.Label>
                                        <Form.Control
                                            type="text"
                                            name="name"
                                            value={formData.name}
                                            onChange={handleChange}
                                        />
                                    </Form.Group>
                                    <Form.Group controlId="surname">
                                        <Form.Label>Фамилия</Form.Label>
                                        <Form.Control
                                            type="text"
                                            name="surname"
                                            value={formData.surname}
                                            onChange={handleChange}
                                        />
                                    </Form.Group>
                                    <Form.Group controlId="avatar">
                                        <Form.Label>Аватар</Form.Label>
                                        <Form.Control
                                            type="file"
                                            name="avatar"
                                            onChange={handleAvatarChange}
                                        />
                                    </Form.Group>
                                    <Button variant="primary" type="submit">Сохранить</Button>
                                </Form>
                            </Modal.Body>
                            <Modal.Footer>
                                <Button variant="secondary" onClick={() => setShowModal(false)}>Закрыть</Button>
                            </Modal.Footer>
                        </Modal>
                    </div>
                </div>
            )}
        </div>
    );
};

export default UserProfile;