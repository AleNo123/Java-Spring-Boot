package com.example.javaProj.service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import com.example.javaProj.model.File;
import com.example.javaProj.repository.FileRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

@Service
public class FileService {
    private FileRepository fileRepository;
    @Value("${file.upload-dir}")
    private String uploadDir;
    private Path rootLocation;
    @Autowired
    public FileService(FileRepository fileRepository){
        this.fileRepository =fileRepository;
    }

    @PostConstruct
    public void init() {
        this.rootLocation = Paths.get(uploadDir);
    }
//    public File createFile(String OriginalFileName, String fileType){
//        File file = new File(); // НАдо что то придумать по созданию пути
//        return file;
//    }
    public File getDefaultAvatar(){
        File defaultAvatar = new File("src/main/resources/static/images/usersIcon/defaultLogo.jpg","defaultLogo","defaultLogo","jpg");
//        fileRepository.save(defaultAvatar);
        return defaultAvatar;
    }
    public File store(MultipartFile file) {
        String filename = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        String subDir = filename.substring(0, 2); // Используем первые два символа UUID в качестве подкаталога
        Path destinationDir = this.rootLocation.resolve(subDir);

        try {
            Files.createDirectories(destinationDir);
            Files.copy(file.getInputStream(), destinationDir.resolve(filename));
            File dbFile = new File(destinationDir.resolve(filename).toString(), filename, file.getOriginalFilename(), file.getContentType());
            fileRepository.save(dbFile);
            return dbFile;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + filename, e);
        }
    }

    public Resource load(String filePath) {
//        String subDir = filename.substring(0, 2); // Используем те же два символа UUID для подкаталога
        try {
            Path file = Paths.get(filePath).normalize();
            if (!file.startsWith(rootLocation) && !file.startsWith(Paths.get("src/main/resources/static"))) {
                throw new SecurityException("Доступ к файлу запрещен.");
            }
            if (Files.exists(file) && !Files.isDirectory(file)) {
                Resource resource = new UrlResource(file.toUri());

                if (resource.exists() && resource.isReadable()) {
                    return resource;
                } else {
                    throw new RuntimeException("Файл не найден или не читается: " + file.getFileName());
                }
            } else {
                throw new RuntimeException("Файл не найден или это директория: " + file.getFileName());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load file", e);
        }
    }
    public Resource loadAsResource(String filePath) {
        try {
            Path file = rootLocation.resolve(filePath);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("File not found or not readable: " + file.getFileName());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load file", e);
        }
    }
    public void delete(File dbFile) {
        Path file = Paths.get(dbFile.getFilePath());
        try {
            // Удаление файла из файловой системы
            Files.deleteIfExists(file);

            // Удаление записи о файле из базы данных
            fileRepository.delete(dbFile);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete file " + dbFile.getOriginalFileName(), e);
        }
    }
    public void delete(Long fileId) {
        // Найти файл по его идентификатору в базе данных
        Optional<File> dbFile = fileRepository.findById(fileId);

        if (dbFile.isPresent()) {
            File fileToDelete = dbFile.get();
            Path file = Paths.get(fileToDelete.getFilePath());

            try {
                // Удаление файла из файловой системы
                Files.deleteIfExists(file);

                // Удаление записи о файле из базы данных
                fileRepository.deleteById(fileId);
            } catch (IOException e) {
                throw new RuntimeException("Failed to delete file " + file, e);
            }
        } else {
            throw new RuntimeException("File not found with id " + fileId);
        }
    }
}
