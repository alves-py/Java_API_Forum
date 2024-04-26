package com.example.csiauth.shared.auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record AuthenticationDTO(@NotBlank @NotNull String login, @NotBlank @NotNull String password) {
}
