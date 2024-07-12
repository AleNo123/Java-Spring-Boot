package com.example.javaProj.repository;


import com.example.javaProj.DTO.UserResponseDTO;
import com.example.javaProj.View.UserView;
import com.example.javaProj.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> { ;
    @EntityGraph(value = "user-with-courses")
    Optional<UserView> findUserViewByUserId(Long id);

    Optional<User> findByName(String name);
    Optional<User> findByNickname(String nickname);
    boolean existsByEmailAddress(String emailAddress);
    boolean existsByNickname(String nickname);

}
