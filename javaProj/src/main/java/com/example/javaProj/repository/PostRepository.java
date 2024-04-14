package com.example.javaProj.repository;

import com.example.javaProj.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT COUNT(DISTINCT profile.profileId) AS num_users_without_posts " +
            "FROM Profile profile " +
            "WHERE profile.profileId NOT IN (SELECT DISTINCT post.profile.profileId FROM Post post)")
    int getCountProfileWithoutPost();
@Query("SELECT post.postId FROM Post post " +
        "WHERE LENGTH(post.content) > 20 " +
        "AND SUBSTRING(post.title, 1, 1) IN ('0', '1', '2', '3', '4', '5', '6', '7', '8', '9') " +
        "AND (SELECT COUNT(comment) FROM Comment comment WHERE comment.post = post) = 2 " +
        "ORDER BY post.postId ASC")
     List<Long> getPostIdWithFilter();
    @Query("SELECT post.postId FROM Post post " +
            "WHERE (SELECT COUNT(comment) FROM Comment comment WHERE comment.post = post) <= 1 " +
            "ORDER BY post.postId ASC " +
            "LIMIT 3")
     List<Long> get3PostIdWithoutComment();
}
