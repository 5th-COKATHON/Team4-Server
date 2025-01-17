package com.example.team4.api.controller;

import com.example.team4.domain.service.PostService;
import com.example.team4.api.dto.request.AddPostRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "게시물 정보", description = "게시물 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @Operation(summary = "일기 등록", description = "일기 등록")
    @PostMapping
    public ResponseEntity<Void> addPosts(@Valid @RequestBody AddPostRequest request) {
        postService.addPost(request.memberId(), request.dateTime(), request.content(), request.emotion(),
                request.latitude(), request.longitude(), request.placeName());
        return ResponseEntity.noContent().build();
    }
}
