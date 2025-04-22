package main.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserEditRequest(
    @NotBlank String name,
    @Email @NotBlank String email
) {}