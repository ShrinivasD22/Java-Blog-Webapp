package com.example.blogappapis2.controllers;



import java.util.LinkedList;
import java.util.Queue;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blogappapis2.exceptions.UserAlreadyExistsException;
import com.example.blogappapis2.payloads.JwtAuthRequest;
import com.example.blogappapis2.payloads.JwtAuthResponse;
import com.example.blogappapis2.payloads.Response;
import com.example.blogappapis2.payloads.UserDto;
import com.example.blogappapis2.security.JwtHelper;
import com.example.blogappapis2.services.UserService;




@RestController
@RequestMapping("/api/auth/")
@CrossOrigin(origins = { "*" })
public class AuthController {

	

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;
    
    @Autowired
    private UserService userServices;
    
  
    
    
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        try {
            UserDto registerUser = this.userServices.registerUser(userDto);
            Response<UserDto> apiResponse = new Response<>("User Registered Successfully!", true, registerUser);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (UserAlreadyExistsException e) {
            Response<UserDto> apiResponse = new Response<>("User with this email already exists", false);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }



    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) {

        this.authenticate(request.getUsername(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.helper.generateToken(userDetails);

        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);
        
        return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
    }
    
    
    private void authenticate(String username, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
           this.manager.authenticate(authentication);

    }

   
}




//try {
//    this.manager.authenticate(authentication);
//
//
// } catch (BadCredentialsException e) {
// 	System.out.println("Invalid details !");
//     throw new BadCredentialsException(" Invalid Username or Password  !!");
// }