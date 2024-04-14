package com.example.javaProj.repository;


import com.example.javaProj.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}