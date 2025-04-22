package main.users.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import main.users.model.Role;
import main.users.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);
    List<User> findAllByRoles(Role role);
}
