package com.gaaji.Gaaji.Eccomerce.controller;/*  gaajiCode
    99
    10/09/2024
    */

import com.gaaji.Gaaji.Eccomerce.dto.LoginRequest;
import com.gaaji.Gaaji.Eccomerce.dto.Responce;
import com.gaaji.Gaaji.Eccomerce.dto.UserDTO;
import com.gaaji.Gaaji.Eccomerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Responce> registerUser(@RequestBody UserDTO registrationRequest){
        System.out.println(registrationRequest);
        return ResponseEntity.ok(userService.registerUser(registrationRequest));
    }
    @PostMapping("/login")
    public ResponseEntity<Responce> loginUser(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(userService.loginUser(loginRequest));
    }
}
