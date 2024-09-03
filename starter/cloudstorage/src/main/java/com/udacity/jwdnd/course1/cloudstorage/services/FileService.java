package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {
    private final FileMapper fileMapper;
    private final UserMapper userMapper;

    public FileService(FileMapper fileMapper, UserMapper userMapper) {
        this.fileMapper = fileMapper;
        this.userMapper = userMapper;
    }

    public int addFile(File file, int userId) throws IOException {
        fileMapper.insertFile(file);

        return file.getFileId();
    }


    public List<File> getAllFiles(int userId) {
        return this.fileMapper.getAllFiles(userId);
    }

    public File getFileById(int fileId, int userId) {
        return this.fileMapper.getFileById(fileId, userId);
    }

    public File getFileByName(String fileName, int userId) {
        return this.fileMapper.getFileByName(fileName, userId);
    }

    public void deleteFileById(int fileId, int userId) {
        this.fileMapper.deleteFile(fileId, userId);
    }

}
