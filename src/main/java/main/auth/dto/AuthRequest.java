package main.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
    @NotBlank String identifier, //username, email
    @NotBlank String password
) {}
