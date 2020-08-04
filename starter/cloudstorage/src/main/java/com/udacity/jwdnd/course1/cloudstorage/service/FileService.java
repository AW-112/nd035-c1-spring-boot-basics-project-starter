package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {
    private final FileMapper fileMapper;
    private final UserService userService;

    public FileService(FileMapper fileMapper, UserService userService) {
        this.fileMapper = fileMapper;
        this.userService = userService;
    }

    public boolean addFile(String username, MultipartFile uploadFile) throws IOException {
        File file = new File(null,
                uploadFile.getOriginalFilename(),
                uploadFile.getContentType(),
                Long.toString(uploadFile.getSize()),
                this.userService.getUser(username).getUserid(),
                uploadFile.getBytes());
        file.setUserid(this.userService.getUser(username).getUserid());
        return this.fileMapper.insertFile(file) > 0;
    }

    public boolean existFilename(String username, String filename) {
        return this.fileMapper.getFile(this.userService.getUser(username).getUserid(), filename) != null;
    }

    public File getFile(String username, String filename) {
        return this.fileMapper.getFile(this.userService.getUser(username).getUserid(),filename);
    }

    public List<File> getFiles(String username) {
        return this.fileMapper.getFiles(this.userService.getUser(username).getUserid());
    }

    public void deleteFile(Integer fileid) {
        this.fileMapper.deleteFile(fileid);
    }
}
