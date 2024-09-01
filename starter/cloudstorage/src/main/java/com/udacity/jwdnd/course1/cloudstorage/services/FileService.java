package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class FileService {
    private final FileMapper fileMapper;
    private final UserMapper userMapper;

    public FileService(FileMapper fileMapper, UserMapper userMapper) {
        this.fileMapper = fileMapper;
        this.userMapper = userMapper;
    }

    public void addFile(MultipartFile multipartFile, int userId) throws IOException {
        InputStream fileInputStream = multipartFile.getInputStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int read;
        byte[] data = new byte[1024];
        while ((read = fileInputStream.read(data)) != -1) {
            buffer.write(data, 0, read);
        }

        buffer.flush();
        byte[] fileData = buffer.toByteArray();

        String fileName = multipartFile.getOriginalFilename();
        String contentType = multipartFile.getContentType();
        String fileSize = String.valueOf(multipartFile.getSize());
        File file = new File(0, fileName, contentType, fileSize, userId, fileData);
        fileMapper.insertFile(file);
    }


    public List<File> getAllFiles(int userId) {
        return this.fileMapper.getAllFiles(userId);
    }

    public File getFileById(int fileId, int userId) {
        return this.fileMapper.getFileById(fileId, userId);
    }

    public void deleteFileById(int fileId, int userId) {
        this.fileMapper.deleteFile(fileId, userId);
    }

}
