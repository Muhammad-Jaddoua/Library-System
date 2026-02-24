package com.library.auth.service;

import com.library.auth.dto.AuthResponse;
import com.library.auth.dto.LoginRequest;
import com.library.auth.dto.RegisterRequest;
import com.library.auth.entity.Role;
import com.library.auth.entity.User;
import com.library.auth.event.UserRegisteredEvent;
import com.library.auth.kafka.AuthEventProducer;
import com.library.auth.repository.UserRepository;
import com.library.auth.security.JwtUtil;
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
    private final AuthEventProducer authEventProducer;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil, AuthEventProducer authEventProducer){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.authEventProducer = authEventProducer;
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
        authEventProducer.publishUserRegistered(new UserRegisteredEvent(
                                                                        newUser.getId(),
                                                                        newUser.getUsername(),
                                                                        newUser.getRole().name())
                                                                        );

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
