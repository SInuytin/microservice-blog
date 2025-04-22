package main.auth.service;

import main.auth.dto.AuthRequest;
import main.auth.dto.AuthResponse;
import main.auth.dto.RegisterRequest;

public interface AuthService {
    public AuthResponse login(AuthRequest request);

    public AuthResponse register(RegisterRequest request);

}
