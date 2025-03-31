package main.users.dto;

import org.springframework.stereotype.Component;

import main.users.model.User;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {
        return new UserResponse(
            user.getId(), 
            user.getName(), 
            user.getEmail()
        );
    }
}
