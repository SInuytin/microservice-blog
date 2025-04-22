package main.users.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import main.common.CurrentUser;
import main.users.dto.UserEditRequest;
import main.users.dto.UserResponse;
import main.users.service.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final CurrentUser currentUser;

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserResponse> getMe() {
        return ResponseEntity.ok(userService.getUserById(currentUser.getId()));
    }

    @GetMapping(params = "name")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserResponse> getUserByName(
            @RequestParam(required = true) String name
    ) {
        return ResponseEntity.ok(userService.getUserByName(name));
    }

    @PutMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserResponse> editMe(
        @RequestBody @Valid UserEditRequest request
    ) {
        return ResponseEntity.ok(userService.editUser(currentUser.getId(), request));
    }

    @DeleteMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deleteMe() {
        userService.deleteUser(currentUser.getId());
        return ResponseEntity.noContent().build();
    }
}