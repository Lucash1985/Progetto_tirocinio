package com.example.Token.controllers;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Token.config.UserAuthProvider;
import com.example.Token.services.UserService;

import jakarta.persistence.criteria.Path;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UploadController {
	
	

	   // private static final String UPLOAD_DIR = "C://Users//Utente Libero//Desktop//arrivati//";  // Directory di destinazione per i file caricati

	    @PostMapping("/api/upload")
	    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
	    	 if (file.isEmpty()) {
	             return ResponseEntity.badRequest().body("No file selected");
	         }

	        System.out.println("arrivato");

	         return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
	    }
	}

