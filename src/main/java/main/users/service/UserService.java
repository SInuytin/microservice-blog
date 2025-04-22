package main.users.service;

import java.util.List;

import main.users.dto.UserCreateRequest;
import main.users.dto.UserEditRequest;
import main.users.dto.UserResponse;
import main.users.model.User;

public interface UserService {
    
    User createUser(UserCreateRequest request);

    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
    UserResponse getUserByName(String name);

    UserResponse editUser(Long id, UserEditRequest request);
    void deleteUser(Long id);
}
