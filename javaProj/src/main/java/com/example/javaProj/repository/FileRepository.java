package com.example.javaProj.repository;

import com.example.javaProj.model.File;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File,Long> {
    @EntityGraph(value = "file")
    Optional<File> getByFileName(String filename);
    Optional<File> getByFileId(Long id);
}
