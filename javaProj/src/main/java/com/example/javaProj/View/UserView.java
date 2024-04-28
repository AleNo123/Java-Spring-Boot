package com.example.javaProj.View;


import com.example.javaProj.model.Course;

import java.util.Set;

public interface UserView {
     Long getUserId();

     String getNickname();

     String getName();

     String getSurname();

     String getPathToUserIcon();

     Set<CourseNameView> getCourses();
}
