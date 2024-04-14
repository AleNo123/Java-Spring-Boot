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
//    @Query("SELECT post.id FROM Post post " +
//            "WHERE (SELECT COUNT(comment) FROM post.comments comment) = 2 " +
//            "AND SUBSTRING(post.title, 1, 1) BETWEEN '0' AND '9' " +
//            "AND LENGTH(post.content) > 20 " +
//            "ORDER BY post.id ASC")
//    public List<Long> getPostIdWithFilter();
//    @Query("SELECT post_id " +
//            "FROM Post post " +
//            "WHERE post_id IN ( " +
//            "    SELECT post_id " +
//            "    FROM Comment comment " +
//            "    GROUP BY post_id " +
//            "    HAVING COUNT(*) <= 1 " +
//            ") " +
//            "OR post_id NOT IN (SELECT DISTINCT post_id FROM Comment comment) " +
//            "ORDER BY post_id " +
//            "LIMIT 3")
//    public List<Long> get3PostIdWithoutComment();
}
