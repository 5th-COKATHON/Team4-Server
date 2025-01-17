package com.example.team4.api.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NicknameRequest(@NotNull @Size(min = 2, max = 10) String nickname) {
}
