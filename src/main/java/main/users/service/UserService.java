package main.users.service;

import java.util.List;

import main.users.dto.UserCreateRequest;
import main.users.dto.UserEditRequest;
import main.users.dto.UserResponse;

public interface UserService {
    
    UserResponse createUser(UserCreateRequest request);

    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);

    UserResponse editUser(Long id, UserEditRequest request);
    void deleteUser(Long id);
}
