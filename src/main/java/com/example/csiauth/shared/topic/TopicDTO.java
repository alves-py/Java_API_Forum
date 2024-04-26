package com.example.csiauth.shared.topic;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record TopicDTO(@NotBlank @NotNull String title, String description) {
}
