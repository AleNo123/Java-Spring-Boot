package com.example.javaProj.View;


import com.example.javaProj.model.Course;
import com.example.javaProj.model.File;

import java.util.Set;

public interface UserView {
     Long getUserId();

     String getNickname();

     String getName();

     String getSurname();

     File getAvatar();

     Set<CourseNameView> getCourses();
}
