package com.example.javaProj.service;

import com.example.javaProj.model.File;
import com.example.javaProj.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FileService {
    private FileRepository fileRepository;
    @Autowired
    public FileService(FileRepository fileRepository){
        this.fileRepository =fileRepository;
    }
    public File createFile(String OriginalFileName, String fileType){
        File file = new File(); // НАдо что то придумать по созданию пути
        return file;
    }
    public File getDefaultAvatar(){
        File defaultAvatar = new File("static/images/usersIcon/defaultLogo","defaultLogo","defaultLogo","jpg");
//        fileRepository.save(defaultAvatar);
        return defaultAvatar;
    }
}
