package com.example.javaProj.Enum;

import com.example.javaProj.model.User;

public enum AccessRights {
    Administrator("Administrator"),
    Moderator("Moderator"),
    Registered("Registered"),
    Editor("Editor"),
    Guest("Guest");
    private String role;
    AccessRights (String role){
        this.role = role;
    }
//    AccessRights getFromUser(User user){
//        return AccessRights(user.get)
//    }

}
