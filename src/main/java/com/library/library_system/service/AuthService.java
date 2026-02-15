package com.library.library_system.service;

import com.library.library_system.dto.AuthResponse;
import com.library.library_system.dto.LoginRequest;
import com.library.library_system.dto.RegisterRequest;
import com.library.library_system.model.Role;
import com.library.library_system.model.User;
import com.library.library_system.repository.UserRepository;
import com.library.library_system.security.jwt.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse register(RegisterRequest registerRequest){
        if(userRepository.existsByUsername(registerRequest.getUsername())){
            throw new RuntimeException("Username already exists");
        }
        //create a new user
        User newUser =  new User();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setEncodedPassword((passwordEncoder.encode(registerRequest.getPassword())));
        newUser.setRole(Role.ROLE_USER);

        userRepository.save(newUser);

        //start a new jwt authentication token
        String token = jwtUtil.generateToken(newUser.getUsername());
        return new AuthResponse(token, newUser.getUsername(), newUser.getRole());
    }

    public AuthResponse login(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        User newUser = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtUtil.generateToken(newUser.getUsername());
        return new AuthResponse(token, newUser.getUsername(), newUser.getRole());

    }

}
