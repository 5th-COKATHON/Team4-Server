package com.example.team4.api.controller;

import com.example.team4.api.dto.request.UpdatePostRequest;
import com.example.team4.api.dto.response.PostDetail;
import com.example.team4.api.dto.response.PostsGroupByLocationResponse;
import com.example.team4.api.dto.response.PostsResponse;
import com.example.team4.domain.service.PostService;
import com.example.team4.api.dto.request.AddPostRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @Operation(summary = "마이페이지용 일기 리스트", description = "마이페이지용 일기 리스트입니다.")
    @GetMapping("/{memberId}/mypage")
    public ResponseEntity<PostsResponse> getPosts(@PathVariable("memberId") Long memberId) {
        return ResponseEntity.ok(postService.findAllByMemberId(memberId));
    }

    @Operation(summary = "지도용 일기 목록 반환", description = "지도용 일기 목록 API")
    @GetMapping("/{memberId}/map")
    public ResponseEntity<PostsGroupByLocationResponse> getPostsMap(@PathVariable("memberId") Long memberId) {
        return ResponseEntity.ok(postService.findAllByMemberIdGroupByLocation(memberId));
    }

    @Operation(summary = "일기 상세 조회", description = "일기 상세 조회")
    @GetMapping("/{postId}")
    public ResponseEntity<PostDetail> getPostInfo(@PathVariable("postId") Long postId) {
        return ResponseEntity.ok(postService.getPostInfo(postId));
    }

    @Operation(summary = "일기 삭제", description = "일기 삭제")
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable("postId") Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "일기 수정", description = "일기 수정")
    @PutMapping("/{postId}")
    public ResponseEntity<Void> updatePost(@PathVariable("postId") Long postId, @Valid @RequestBody UpdatePostRequest request) {
        postService.updatePost(postId, request.dateTime(), request.content(), request.emotion(),
                request.latitude(), request.longitude(), request.placeName());
        return ResponseEntity.ok().build();
    }
}
