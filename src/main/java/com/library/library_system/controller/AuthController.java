package com.library.library_system.controller;

import com.library.library_system.dto.AuthRequest;
import com.library.library_system.dto.AuthResponse;
import com.library.library_system.dto.LoginRequest;
import com.library.library_system.dto.RegisterRequest;
import com.library.library_system.security.jwt.JwtUtil;
import com.library.library_system.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final AuthService authService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authService.register(registerRequest));

    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }
//    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        authRequest.getUsername(),
//                        authRequest.getPassword()
//                )
//        );
//        String token = jwtUtil.generateToken(authRequest.getUsername());
//        return ResponseEntity.ok(new AuthResponse(token));
//    }
}
