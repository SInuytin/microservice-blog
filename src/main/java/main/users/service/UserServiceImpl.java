package main.users.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import main.users.dto.UserCreateRequest;
import main.users.dto.UserEditRequest;
import main.users.dto.UserMapper;
import main.users.dto.UserResponse;
import main.users.exceptions.EmailAlreadyExistsException;
import main.users.exceptions.UserNotFoundException;
import main.users.model.User;
import main.users.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    private User getUser(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));
    }    

    @Override
    public UserResponse createUser(UserCreateRequest request) {
        if (repository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }
        User newUser = new User();
        newUser.setName(request.getName());
        newUser.setEmail(request.getEmail());
        String hash = passwordEncoder.encode(request.getPassword());
        newUser.setPasswordHash(hash);

        User saved = repository.save(newUser);
        
        return userMapper.toResponse(saved);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return repository.findAll().stream()
            .map(userMapper::toResponse)
            .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = getUser(id);
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse editUser(Long id, UserEditRequest request) {
        User user = getUser(id);
        if (!user.getEmail().equals(request.getEmail()) &&
            repository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        user.setEmail(request.getEmail());
        user.setName(request.getName());
        repository.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    public void deleteUser(Long id) {
        getUser(id);
        repository.deleteById(id);
    }
    
}
