package main.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import main.auth.dto.AuthRequest;
import main.auth.dto.AuthResponse;
import main.auth.dto.RegisterRequest;
import main.security.jwt.JwtService;
import main.users.dto.UserMapper;
import main.users.service.UserService;
import main.users.model.User;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    
    private final UserMapper mapper;
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthResponse login(AuthRequest request) {
        var authentication = new UsernamePasswordAuthenticationToken(
            request.identifier(), 
            request.password()
        );

        Authentication authResult = authenticationManager.authenticate(authentication);

        UserDetails userDetails = (UserDetails) authResult.getPrincipal();
        return new AuthResponse(
            jwtService.generateToken(userDetails)
        );
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        User user = userService.createUser(mapper.toCreateRequest(request));
        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }
    
}
