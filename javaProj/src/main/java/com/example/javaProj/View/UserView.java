package com.example.javaProj.View;


import com.example.javaProj.model.Course;
import com.example.javaProj.model.File;

import java.util.List;
import java.util.Set;

public interface UserView {
     Long getUserId();

     String getNickname();

     String getName();

     String getSurname();
     String getEmailAddress();
     List<String> getContactLinks();
     String getAchievements();

     File getAvatar();

     Set<CourseNameView> getCourses();
}
