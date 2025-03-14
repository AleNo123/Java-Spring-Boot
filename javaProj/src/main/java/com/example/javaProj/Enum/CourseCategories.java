package com.example.javaProj.Enum;

public enum CourseCategories {
    CATEGORY1("CATEGORY1"),
    CATEGORY2("CATEGORY2"),
    CATEGORY3("CATEGORY3"),
    CATEGORY4("CATEGORY4"),
    CATEGORY5("CATEGORY5");
    private String category;
    CourseCategories (String category){
        this.category = category;
    }
//    AccessRights getFromUser(User user){
//        return AccessRights(user.get)
//    }
}
