package com.example.team4.api.dto.response;

import com.example.team4.domain.Emotion;
import com.example.team4.domain.entity.Post;
import java.time.LocalDate;

public record PostInfo(
        Long postId,
        Double latitude,
        Double longitude,
        String placeName,
        String context,
        Emotion emotion,
        LocalDate date
) {
    public static PostInfo from(Post post) {
        return new PostInfo(post.getId(), post.getLatitude(), post.getLongitude(), post.getPlaceName(),
                post.getContext(), post.getEmotion(), post.getDate());
    }
}
