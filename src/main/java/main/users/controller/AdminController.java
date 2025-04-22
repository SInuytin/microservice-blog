package main.users.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import main.users.dto.UserEditRequest;
import main.users.dto.UserResponse;
import main.users.service.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> editUser(
            @PathVariable Long id, @RequestBody @Valid UserEditRequest request) {
        return ResponseEntity.ok(userService.editUser(id, request));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}/roles/admin ")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> setAdmin(
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(userService.setAdmin(id));
    }

    @GetMapping("/admins")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> getAllAdmins() {
        return ResponseEntity.ok(userService.getAllAdmins());
    }

    @DeleteMapping("/{id}/roles/admin ")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAdmin(
        @PathVariable Long id
    ) {
        userService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }
}
