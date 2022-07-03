package com.java.demo.controller;

import com.java.demo.datastore.model.UserModel;
import com.java.demo.datastore.repositories.UserRepository;
import com.java.demo.pojos.AuthenticateRequest;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribe(@RequestBody AuthenticateRequest authenticateRequest){

/*        String username = authenticateRequest.getUsername();

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode(authenticateRequest.getPassword());

        String role = "ADMIN";
        Boolean isActive = true;

        UserModel userModel = new UserModel(username, password, role,  isActive);
        userRepository.save(userModel);*/


        return ResponseEntity.ok(
                userRepository.save(new UserModel(
                                authenticateRequest.getUsername(),
                                new BCryptPasswordEncoder().encode(authenticateRequest.getPassword()),
                                "ADMIN", true)));

    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticateRequest authenticateRequest){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticateRequest.getUsername(), authenticateRequest.getPassword()));

        return ResponseEntity.ok("Authentication successful for "+authenticateRequest.getUsername());
    }

}
