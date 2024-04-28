package com.example.javaProj.service;

import com.example.javaProj.DTO.UserRequestDTO;
import com.example.javaProj.View.UserView;
import com.example.javaProj.Enum.AccessRights;
import com.example.javaProj.model.User;
import com.example.javaProj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    public UserView getUserById(Long id){
//        UserResponseDTO responseDTO = repository.findById(id);

        return repository.findUserViewById(id).orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }
    public void createUser(UserRequestDTO requestDTO){
        User user = requestDTO.convertToUser();
        user.setPathToUserIcon("static/images/usersIcon/defaultLogo");
        user.setAccessRights(AccessRights.Registered.name());
        repository.save(user);
    }

}
