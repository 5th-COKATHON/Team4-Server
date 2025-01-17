package com.example.team4.api.dto.response;

import java.util.List;

public record PostInfoGroupByLocation(
        Double latitude,
        Double longitude,
        List<PostInfo> postInfos
) {
}
