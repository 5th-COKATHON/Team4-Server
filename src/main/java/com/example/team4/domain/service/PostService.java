package com.example.team4.domain.service;

import com.example.team4.api.dto.response.PostsResponse;
import com.example.team4.domain.Emotion;
import com.example.team4.domain.entity.Member;
import com.example.team4.domain.entity.Post;
import com.example.team4.domain.repository.MemberRepository;
import com.example.team4.domain.repository.PostRepository;
import com.example.team4.global.error.AppException;
import com.example.team4.global.error.ErrorCode;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void addPost(Long memberId, LocalDate localDate, String content, Emotion emotion,
                        Double latitude, Double longitude, String placeName) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new AppException(ErrorCode.MEMBER_NOT_FOUND));
        Post newPost = Post.of(member, emotion, localDate, placeName, latitude, longitude, content);
        postRepository.save(newPost);
    }

    public PostsResponse findAllByMemberId(final Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new AppException(ErrorCode.MEMBER_NOT_FOUND));
        return PostsResponse.from(postRepository.findAllByMember(member));
    }
}
