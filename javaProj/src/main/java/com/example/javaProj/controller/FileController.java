package com.example.javaProj.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileController {
//    @GetMapping("/static/{folder}/{fileName:.+}")
//    @ResponseBody
//    public ResponseEntity<Resource> getStaticFile(@PathVariable String folder, @PathVariable String fileName) {
//        System.out.println("xzxvcxzvxcvxzcvxcvz");
//        System.out.println(folder);
//        System.out.println(fileName);
//        Resource file = new ClassPathResource("static/" + folder + "/" + fileName);
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
//                .body(file);
//    }
    @GetMapping("/images/{folder}/{fileName:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getStaticFile(@PathVariable String folder, @PathVariable String fileName) {
        Resource file = new ClassPathResource("static/images/" + folder + "/" + fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
