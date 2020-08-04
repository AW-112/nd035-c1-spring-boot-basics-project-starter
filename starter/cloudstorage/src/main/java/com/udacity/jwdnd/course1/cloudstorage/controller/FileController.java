package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = "/files")
    public String addFile(Authentication auth, MultipartFile fileUpload, Model model) {
        try {
            if (fileUpload.isEmpty()) {
                model.addAttribute("errorMessage", "File doesn't exist!");
            } else if (this.fileService.existFilename(auth.getName(), fileUpload.getOriginalFilename())) {
                model.addAttribute("errorMessage", "No double filename allowed!");
            } else if (!this.fileService.addFile(auth.getName(), fileUpload)) {
                model.addAttribute("errorMessage", "There was an error adding the file. Please try again!");
            }

            return "result";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred while processing the request!<br>" + e.toString());
            return "result";
        }
    }

    @GetMapping("/files/delete/{fileid}")
    public String deleteFile(@PathVariable Integer fileid) {
        this.fileService.deleteFile(fileid);
        return "result";
    }

    @GetMapping("/files/download")
    public ResponseEntity download(Authentication auth, @RequestParam String filename) {
        File file = this.fileService.getFile(auth.getName(), filename);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(file.getFiledata());
    }
}
