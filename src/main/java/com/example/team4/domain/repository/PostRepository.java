package com.example.team4.domain.repository;

import com.example.team4.domain.entity.Member;
import com.example.team4.domain.entity.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByMember(Member member);
}
