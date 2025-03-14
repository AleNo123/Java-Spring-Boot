package com.example.javaProj.Enum;

import com.example.javaProj.model.User;

public enum AccessRights {
    OWNER("OWNER"),
    ADMIN("ADMIN"),
    TEACHER("TEACHER"),
    USER("USER");
    private String role;
    AccessRights (String role){
        this.role = "ROLE_"+role;
    }
//    AccessRights getFromUser(User user){
//        return AccessRights(user.get)
//    }

}
