package main.users.dto;

import org.springframework.stereotype.Component;

import main.auth.dto.RegisterRequest;
import main.users.model.User;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {
        return new UserResponse(
            user.getId(), 
            user.getName()
        );
    }
    public UserCreateRequest toCreateRequest(RegisterRequest request) {
        return new UserCreateRequest(
            request.name(), 
            request.email(), 
            request.password()
        );
    }
}
