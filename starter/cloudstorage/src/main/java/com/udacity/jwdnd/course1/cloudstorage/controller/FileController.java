package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.exceptions.ArgsNotFoundException;
import com.udacity.jwdnd.course1.cloudstorage.exceptions.NoRowsAffectedException;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URI;

@Controller
@RequestMapping("file")
public class FileController {

    private final FileService fileService;
    private final UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping
    public String upload(
            @RequestParam("fileUpload") MultipartFile file,
            Authentication authentication,
            RedirectAttributes model
    ) {
        String uploadErrorMessage = null;
        try {
            if (file.getSize() == 0 || file.getOriginalFilename() == null || file.getOriginalFilename().isEmpty()) {
                throw new ArgsNotFoundException("Uploaded file is empty");
            }
            if (file.getSize() / 1024 / 1024 > 5) {
                throw new NoRowsAffectedException("File exceeded limit 5 MB");
            }
            int userId = userService.getUser(authentication.getName()).getUserId();
            File fileModel = new File(null, file.getOriginalFilename(), file.getContentType(),
                    String.format("%d KB", file.getSize() / 1024), userId, file.getInputStream().readAllBytes());
            fileService.addFile(fileModel, userId);
        } catch (Exception e) {
            uploadErrorMessage = "Unexpected error, please try again.";
        }
        model.addFlashAttribute("resultError", uploadErrorMessage);

        return "redirect:/home/result";
    }

    @GetMapping
    public ResponseEntity<StreamingResponseBody> view(@ModelAttribute("fileId") int fileId,
                                                      Authentication authentication, RedirectAttributes model) {
        try {
            int userId = userService.getUser(authentication.getName()).getUserId();
            File file = fileService.getFileById(fileId, userId);
            StreamingResponseBody responseBody = outputStream -> outputStream.write(file.getFileData());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment;fileid=%s", fileId))
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(responseBody);
        } catch (Exception e) {
            model.addFlashAttribute("resultError", "Unexpected error, please try again.");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/home/result"));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @PostMapping("delete")
    public String delete(@ModelAttribute("fileId") int fileId, Authentication authentication) {
        int userId = userService.getUser(authentication.getName()).getUserId();
        fileService.deleteFileById(fileId, userId);

        return "redirect:/home/result";
    }

}
