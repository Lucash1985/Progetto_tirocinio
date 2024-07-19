package com.example.Token.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.Token.config.UserAuthProvider;
import com.example.Token.dto.UserDto;

import security.CustomuserDetailsService;


@RestController
public class MessageController {

    private final CustomuserDetailsService userDetailsService;
    private final UserAuthProvider userAuthProvider;

    @Autowired
    public MessageController(CustomuserDetailsService userDetailsService, UserAuthProvider userAuthProvider) {
        this.userDetailsService = userDetailsService;
        this.userAuthProvider = userAuthProvider;
    }

    @GetMapping("/messages")
    public ResponseEntity<ArrayList<String>> messages(@RequestHeader("Authorization") String token) {
    	// Estrarre il login dal token
        String login = userAuthProvider.getLogin(token.replace("Bearer ", ""));

        // Ottenere i dettagli dell'utente utilizzando il login
        UserDetails userDetails = userDetailsService.loadUserByUsername(login);

        // Estrarre i ruoli dell'utente
        ArrayList<String> roles = (ArrayList<String>) userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        for (String role : roles) {
            System.out.println(role);
        }

        return ResponseEntity.ok(roles);
    }
}

/*
@RestController
public class MessagesController {
	private CustomuserDetailsService service;
	@Autowired
	private  CustomuserDetailsService Device;
	@GetMapping("/messages")
    public ResponseEntity<List<String>> messages(@RequestHeader("Authorization") String token) {
       UserAuthProvider a  = new UserAuthProvider();
        String login =  a.getLogin(token);
        String role = 
        // Esempio di restituzione di dati basati sul login (può essere personalizzato in base alle tue esigenze)
        List<String> messages = Arrays.asList("Message 1 for " + login, "Message 2 for " + login);
        
        return ResponseEntity.ok(messages);
    }

 
}
*/
/*
 *  public ResponseEntity<List<String>> messages(@RequestHeader("Authorization") String token) {
       UserAuthProvider a  = new UserAuthProvider();
        String login =  a.getLogin(token);
     
        // Esempio di restituzione di dati basati sul login (può essere personalizzato in base alle tue esigenze)
        List<String> messages = Arrays.asList("Message 1 for " + login, "Message 2 for " + login);
        
        return ResponseEntity.ok(messages);
    }

 * */

/*
 * 
	 @GetMapping("/messages")
	    public ResponseEntity<ArrayList<String>> messages(@RequestHeader("Authorization") String token) {
	     ArrayList<String> roleList = new ArrayList<String>();   
		 String login = userAuthProvider.getLoginFromJwt(token.replace("Bearer ", ""));
	        UserDetails userDetails = customUserDetailsService.loadUserByUsername(login);
	        userDetails.getAuthorities().forEach(authority -> 
           roleList.add(authority.getAuthority()));
	        return ResponseEntity.ok(roleList);
	    }
	}*/
 /*ArrayList<String> Arrays = new ArrayList<>();
	UserAuthProvider a = new UserAuthProvider();
	Authentication ath = a.validateToken(token);
	UserDto user = (UserDto)ath.getPrincipal();
	String login = user.getLogin();
	Arrays.add(login);
	return ResponseEntity.ok(Arrays);*/