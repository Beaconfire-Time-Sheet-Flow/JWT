package com.example.hrinterface.controller;

import com.example.hrinterface.entity.DigitalDocument;
import com.example.hrinterface.response.UploadFileResponse;
import com.example.hrinterface.service.DocumentService;
import com.example.hrinterface.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private DocumentService documentService;

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/uploadDigitalFile")
    public ResponseEntity<DigitalDocument> uploadDigitalFile(
            @RequestParam("file") MultipartFile file
    ){
        UploadFileResponse response = uploadFile(file);
        DigitalDocument digitalDocument = new DigitalDocument();
        digitalDocument.setType("I983");
        digitalDocument.setRequired(true);
        digitalDocument.setTemplateLocation(response.getPath());
        digitalDocument.setDescription(response.getFileName());
        documentService.addNewDigitalDoc(digitalDocument);
        return ResponseEntity.ok(digitalDocument);
    }

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(
            @RequestParam("file")MultipartFile file
            ){
        String fileName = fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
