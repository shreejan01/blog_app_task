package com.blog.blogapp.service.impl;

import com.blog.blogapp.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    //upload image
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        //file name
        String name = file.getOriginalFilename();

        //random name generate file
        String randomID = UUID.randomUUID().toString();
        assert name != null;
        String fileName = randomID.concat(name.substring(name.lastIndexOf(".")));

        //full path
        String filePath = path+ File.separator+fileName;

        //create folder if not exists
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }

        //copy file
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return fileName;
    }

    //retrieve image
    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path+File.separator+fileName;

        return new FileInputStream(fullPath);
    }


}
