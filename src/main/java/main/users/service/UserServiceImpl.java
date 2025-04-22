package main.users.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import main.users.dto.UserCreateRequest;
import main.users.dto.UserEditRequest;
import main.users.dto.UserMapper;
import main.users.dto.UserResponse;
import main.users.exceptions.EmailAlreadyExistsException;
import main.users.exceptions.NameAlreadyExistsException;
import main.users.exceptions.UserNotFoundException;
import main.users.model.Role;
import main.users.model.User;
import main.users.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    private User getUser(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User createUser(UserCreateRequest request) {
        if (repository.findByEmail(request.email()).isPresent()) {
            throw new EmailAlreadyExistsException(request.email());
        }
        if (repository.findByName(request.name()).isPresent()) {
            throw new NameAlreadyExistsException(request.name());
        }
        User newUser = new User();
        newUser.setName(request.name());
        newUser.setEmail(request.email());
        Set<Role> roles = Set.of(Role.ROLE_USER);
        newUser.setRoles(roles);
        String hash = passwordEncoder.encode(request.password());
        newUser.setPasswordHash(hash);

        User saved = repository.save(newUser);
        
        return saved;
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
        if (!user.getEmail().equals(request.email()) &&
                repository.findByEmail(request.email()).isPresent()) {
            throw new EmailAlreadyExistsException(request.email());
        }

        user.setEmail(request.email());
        user.setName(request.name());
        repository.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    public void deleteUser(Long id) {
        getUser(id);
        repository.deleteById(id);
    }

    @Override
    public UserResponse getUserByName(String name) {
        Optional<User> user = repository.findByName(name);
        if (!user.isPresent()) {
            throw new NameAlreadyExistsException(name);
        }
        return userMapper.toResponse(user.get());
    }

    @Override
    public UserResponse setAdmin(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAdmin'");
    }

    @Override
    public List<UserResponse> getAllAdmins() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllAdmins'");
    }

    @Override
    public void deleteAdmin(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAdmin'");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByName(username)
            .orElseThrow(() -> new UsernameNotFoundException(username)); //Link up with UserNotFoundException maybe
    }

}
