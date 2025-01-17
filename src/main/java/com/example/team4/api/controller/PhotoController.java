package com.example.team4.api.controller;

import com.example.team4.domain.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "일기 사진", description = "일기 사진")
@RestController
@RequiredArgsConstructor
@RequestMapping("/photos")
public class PhotoController {
    private final PostService postService;

    @Operation(summary = "일기 사진 추가", description = "일기 사진 추가")
    @PostMapping(value = "/{postId}", consumes = "multipart/form-data")
    public ResponseEntity<Void> addPhoto(@PathVariable("postId") Long postId, @RequestParam MultipartFile file) {
        postService.addPhoto(postId, file);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "일기 사진 삭제", description = "일기 사진 삭제")
    @DeleteMapping
    public ResponseEntity<Void> deletePost(@PathVariable("postId") Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok().build();
    }
}
