package main.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import main.posts.exceptions.PostNotFoundException;
import main.posts.exceptions.TitleDoesNotExistException;
import main.users.exceptions.EmailAlreadyExistsException;
import main.users.exceptions.NameAlreadyExistsException;
import main.users.exceptions.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ==USER_EXCEPTIONS==
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailConflict(EmailAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(NameAlreadyExistsException.class)
    public ResponseEntity<String> handleNameConflict(NameAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    // ==POST_EXCEPTIONS==
    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<String> handlePostNotFound(PostNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(TitleDoesNotExistException.class)
    public ResponseEntity<String> handleTitleDoesNotExist(TitleDoesNotExistException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}
