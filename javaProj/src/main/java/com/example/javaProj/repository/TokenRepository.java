package com.example.javaProj.repository;

import com.example.javaProj.model.Token;
import com.example.javaProj.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {
    public Optional<Token> getByToken(String token);
    boolean existsByUser(User user);
    Optional<Token> findByUser(User user);
}
