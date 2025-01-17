package com.example.team4.api.dto.response;

import java.util.List;

public record PostsGroupByLocationResponse(
        List<PostInfoGroupByLocation> postInfosGroupByLocation
) {
    public static PostsGroupByLocationResponse from(List<PostInfoGroupByLocation> postInfoGroupByLocation) {
        return new PostsGroupByLocationResponse(postInfoGroupByLocation);
    }
}
