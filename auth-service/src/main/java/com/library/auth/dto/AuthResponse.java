package com.library.auth.dto;
import com.library.auth.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String username;
    private Role role;

    public AuthResponse(String token) {
        this.token = token;
    }

}