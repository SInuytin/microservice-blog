package main.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import main.friends.exceptions.FriendshipDontExistException;
import main.friends.exceptions.WrongFriendshipStatusException;
import main.friends.model.FriendshipStatus;
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

    // ==FRIENDSHIP_EXCEPTIONS==

    @ExceptionHandler(FriendshipDontExistException.class)
    public ResponseEntity<String> handleFriendshipDontExist(FriendshipDontExistException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    @ExceptionHandler(WrongFriendshipStatusException.class)
    public ResponseEntity<String> handleWrongFriendshipStatus(WrongFriendshipStatusException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
