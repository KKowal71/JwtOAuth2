package com.jkprojects.jwtuauth.controller;

import com.jkprojects.jwtuauth.model.AuthenticationResponse;
import com.jkprojects.jwtuauth.model.LoginRequest;
import com.jkprojects.jwtuauth.model.RegisterRequest;
import com.jkprojects.jwtuauth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/1/auth")
@RequiredArgsConstructor
public class AuthenticationConroller {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request) throws Exception {
        return ResponseEntity.ok(authenticationService.login(request));
    }

}
