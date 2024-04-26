package com.example.csiauth.shared.user;

import com.example.csiauth.model.users.UserRole;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record RegisterDTO(@NotBlank @NotNull String login, @NotBlank @NotNull String name , @NotBlank @NotNull String password, UserRole role) {
}
