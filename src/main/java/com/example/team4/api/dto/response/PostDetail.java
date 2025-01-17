package com.example.team4.api.dto.response;

import com.example.team4.domain.Emotion;
import com.example.team4.domain.entity.Post;
import java.time.LocalDate;

public record PostDetail(
        Long postId,
        Double latitude,
        Double longitude,
        String placeName,
        Emotion emotion,
        LocalDate date,
        String content
) {
    public static PostDetail from(Post post) {
        return new PostDetail(post.getId(), post.getLatitude(), post.getLongitude(), post.getPlaceName(),
                post.getEmotion(), post.getDate(), post.getContext());
    }
}
