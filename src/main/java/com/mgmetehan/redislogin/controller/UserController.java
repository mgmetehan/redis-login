package com.mgmetehan.redislogin.controller;

import com.mgmetehan.redislogin.dto.CreateUserDto;
import com.mgmetehan.redislogin.dto.LoginUserDto;
import com.mgmetehan.redislogin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping()
    private ResponseEntity<String> createUser(@RequestBody CreateUserDto dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @PostMapping("/login")
    private ResponseEntity<String> login(@RequestBody LoginUserDto dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();

        if (Objects.isNull(username) || Objects.isNull(password)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
        }

        String token = userService.loginAndGetToken(username, password);

        if (token != null) {
            return ResponseEntity.ok("Login successful! Token: " + token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
