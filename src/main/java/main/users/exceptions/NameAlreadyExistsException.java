package main.users.exceptions;

public class NameAlreadyExistsException extends RuntimeException {
    public NameAlreadyExistsException(String name) {
        super("This name: " + name + ", already in use.");
    }
}
