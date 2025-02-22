package com.example.team4.domain.service;

import com.example.team4.api.dto.response.PostDetail;
import com.example.team4.api.dto.response.PostInfo;
import com.example.team4.api.dto.response.PostInfoGroupByLocation;
import com.example.team4.api.dto.response.PostsGroupByLocationResponse;
import com.example.team4.api.dto.response.PostsResponse;
import com.example.team4.domain.Emotion;
import com.example.team4.domain.entity.Member;
import com.example.team4.domain.entity.Photo;
import com.example.team4.domain.entity.Post;
import com.example.team4.domain.entity.S3Info;
import com.example.team4.domain.repository.MemberRepository;
import com.example.team4.domain.repository.PhotoRepository;
import com.example.team4.domain.repository.PostRepository;
import com.example.team4.global.error.AppException;
import com.example.team4.global.error.ErrorCode;
import com.example.team4.global.s3.S3Uploader;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final S3Uploader s3Uploader;
    private final PhotoRepository photoRepository;

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

    public PostsGroupByLocationResponse findAllByMemberIdGroupByLocation(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new AppException(ErrorCode.MEMBER_NOT_FOUND));

        // Group posts by latitude and longitude
        Map<String, List<Post>> groupedPosts = postRepository.findAllByMember(member).stream()
                .collect(Collectors.groupingBy(post -> generateKey(post.getLatitude(), post.getLongitude())));

        // Convert grouped data into PostInfoGroupByLocation
        List<PostInfoGroupByLocation> responses = groupedPosts.entrySet().stream()
                .map(entry -> convertToGroupByLocation(entry.getKey(), entry.getValue()))
                .toList();

        return new PostsGroupByLocationResponse(responses);
    }

    private String generateKey(Double latitude, Double longitude) {
        return latitude + " " + longitude;
    }

    private PostInfoGroupByLocation convertToGroupByLocation(String key, List<Post> posts) {
        String[] coordinates = key.split(" ");
        Double latitude = Double.parseDouble(coordinates[0]);
        Double longitude = Double.parseDouble(coordinates[1]);

        List<PostInfo> postInfos = posts.stream()
                .map(PostInfo::from)
                .toList();

        return new PostInfoGroupByLocation(latitude, longitude, postInfos);
    }

    public PostDetail getPostInfo(final Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));
        return PostDetail.from(post);
    }

    @Transactional
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Transactional
    public void updatePost(Long postId, LocalDate localDate, String content, Emotion emotion,
                           Double latitude, Double longitude, String placeName) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));
        post.update(localDate, content, emotion, latitude, longitude, placeName);
    }

    @Transactional
    public void addPhoto(Long postId, MultipartFile file) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));

        S3Info s3Info = s3Uploader.uploadFiles(file, "/member");
        photoRepository.save(Photo.of(s3Info, post));
    }

    @Transactional
    public void deletePhoto(Long photoId) {
        Photo photo = photoRepository.findById(photoId)
                .orElseThrow(() -> new AppException(ErrorCode.PHOTO_NOT_FOUND));
        s3Uploader.deleteFile(photo.getS3Info());
    }
}
