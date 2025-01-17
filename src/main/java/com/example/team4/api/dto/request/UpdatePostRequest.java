package com.example.team4.api.dto.request;

import com.example.team4.domain.Emotion;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record UpdatePostRequest(
        @NotNull
        Emotion emotion,
        @NotNull
        LocalDate dateTime,
        @NotNull
        Double latitude,
        @NotNull
        Double longitude,
        String placeName,
        String content
) {
}
