package com.example.javaProj.controller;

import com.example.javaProj.api.UserPrivateControllerAPI;
import com.example.javaProj.model.File;
import com.example.javaProj.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/private/files")
public class FileController {
    private final FileService fileService;
    private final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            File dbFile = fileService.store(file);
            return ResponseEntity.ok("File uploaded successfully: " + dbFile.getOriginalFileName());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file: " + e.getMessage());
        }
    }

    @GetMapping("/load")
    public ResponseEntity<Resource> getFile(@RequestParam String filePath) {
        try {
            Resource file = fileService.load(filePath);
//            logger.info(file);
            return ResponseEntity.ok(file);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{fileId}")
    public ResponseEntity<String> deleteFile(@PathVariable Long fileId) {
        try {
            fileService.delete(fileId);
            return ResponseEntity.ok("File deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to delete file: " + e.getMessage());
        }
    }
//    @GetMapping("/images/{folder}/{fileName:.+}")
//
//    public ResponseEntity<Resource> getStaticFile(@PathVariable String folder, @PathVariable String fileName) {
//        Resource file = new ClassPathResource("static/images/" + folder + "/" + fileName);
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
//                .body(file);
//    }
}

