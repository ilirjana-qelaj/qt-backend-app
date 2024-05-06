package com.qelaj.trockenbau.app.controller;

import com.qelaj.trockenbau.app.service.FileAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/file-attachment")
public class FileAttachmentController {

    private final FileAttachmentService fileAttachmentService;

    @Autowired
    public FileAttachmentController(FileAttachmentService fileAttachmentService) {
        this.fileAttachmentService = fileAttachmentService;
    }

    @PostMapping("/upload")
    private ResponseEntity<?> saveFile(@RequestParam MultipartFile file) throws IOException, SQLException {
        fileAttachmentService.saveFile(file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    private ResponseEntity<?> getAllFiles(){
        return new ResponseEntity<>(fileAttachmentService.getFiles(),HttpStatus.OK);
    }

    @PatchMapping("/update-description")
    private ResponseEntity<?> saveFile(@RequestParam Long id,@RequestParam String description) throws IOException, SQLException {
        int status = fileAttachmentService.updateDescription(id,description);
        return new ResponseEntity<>(HttpStatus.valueOf(status));
    }

    @DeleteMapping("/delete")
    private ResponseEntity<?> deleteFile(@RequestParam Long id) throws IOException, SQLException {
        int status = fileAttachmentService.deleteFile(id);
        return new ResponseEntity<>(HttpStatus.valueOf(status));
    }
}
