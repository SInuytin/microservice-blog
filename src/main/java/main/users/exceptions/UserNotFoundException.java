package main.users.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id) {
        super("User with id: " + id +", not found");
    }
    public UserNotFoundException(String name) {
        super("User: " + name + ", not found");
    }
}
