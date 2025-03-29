package main.users.service;

import java.util.List;
import java.util.Optional;

import main.users.dto.UserCreateRequest;
import main.users.dto.UserEditRequest;
import main.users.dto.UserResponse;

public interface UserService {
    
    UserResponse createUser(UserCreateRequest request);

    List<UserResponse> getAllUsers();
    Optional<UserResponse> getUserById(Long id);

    Optional<UserResponse> editUser(Long Id, UserEditRequest request);
    boolean deleteUser(Long id);
}
