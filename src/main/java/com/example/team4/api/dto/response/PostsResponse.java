package com.example.team4.api.dto.response;

import com.example.team4.domain.entity.Post;
import java.util.List;

public record PostsResponse(
        List<PostInfo> posts
) {
    public static PostsResponse from(List<Post> posts) {
        return new PostsResponse(posts.stream()
                .map(PostInfo::from)
                .toList());
    }
}
