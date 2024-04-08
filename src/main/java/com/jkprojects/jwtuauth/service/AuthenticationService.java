package com.jkprojects.jwtuauth.service;

import com.jkprojects.jwtuauth.model.*;
import com.jkprojects.jwtuauth.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .name(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse login(LoginRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getUsernameOrEmail(),
//                        request.getPassword()
//                )
//        );
        User user;
        Map<String, Object> extraClaims = new HashMap<>();
        if (request.getUsernameOrEmail().contains("@")) {
            user = userRepository.findByEmail(request.getUsernameOrEmail()).orElseThrow();
        } else {
            user = userRepository.findByUsername(request.getUsernameOrEmail()).orElseThrow();
            extraClaims.put("email", user.getEmail());
        }
        extraClaims.put("role", user.getRole());
        extraClaims.put("id", user.getId());
        extraClaims.put("name", user.getName());
        var jwtToken = jwtService.generateToken(extraClaims, user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
