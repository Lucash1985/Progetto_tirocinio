package com.example.Token.controllers;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Token.config.UserAuthProvider;
import com.example.Token.dto.CredentialsDto;
import com.example.Token.dto.SignUpDto;
import com.example.Token.dto.UserDto;
import com.example.Token.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserAuthProvider userAuthProvider;
    private static String UPLOAD_DIR = "C://Users//Utente Libero//Desktop//arrivati//";

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto) {
    	System.out.println("ciao");
    	 System.out.println(credentialsDto.password());
    	UserDto userDto = userService.login(credentialsDto);
        userDto.setToken(userAuthProvider.createToken(userDto));
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUpDto) {
        System.out.println("cciao");
    	UserDto user = userService.register(signUpDto);
        user.setToken(userAuthProvider.createToken(user));
        return ResponseEntity.created(URI.create("/users/" + user.getId())).body(user);
    }
    
   
 /*   public void leggiZip(){
    	String zipFilePath = "C:\\Users\\Giulio\\eclipse-workspace\\ProveFiles\\src\\Files\\Desktop.zip";
        
        // Creare un oggetto File per il file zip
        File file = new File(zipFilePath);
        
        // Verificare se il file esiste e non è una directory
        if (file.exists() && !file.isDirectory()) {
            try (ZipFile zipFile = new ZipFile(file)) {
                // Ottenere un elenco di voci del file zip
                Enumeration<? extends ZipEntry> entries = zipFile.entries();
                
                // Iterare attraverso le voci e stampare i nomi dei file
                while (entries.hasMoreElements()) {
                    ZipEntry entry = entries.nextElement();
                    System.out.println(entry.getName());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Il file specificato non esiste o è una directory.");
        }
    
}*/
}
