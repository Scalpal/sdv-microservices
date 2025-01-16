package com.example.sdvauthapi.controller;

import com.example.sdvauthapi.dto.UserDto;
import com.example.sdvauthapi.entity.AuthResponse;
import com.example.sdvauthapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<String> test () {
        System.out.println("caca");
        return new ResponseEntity<>("caca", HttpStatus.OK);
    }

    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<String> register (@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.register(userDto), HttpStatus.OK);
    }

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<AuthResponse> login (@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.login(userDto), HttpStatus.OK);
    }
}
