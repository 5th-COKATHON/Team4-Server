package com.example.team4.domain.entity;

import com.example.team4.domain.Emotion;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "emotion", nullable = false)
    @Enumerated(EnumType.STRING)
    private Emotion emotion;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "place_name")
    private String placeName;

    @Column(name = "context", columnDefinition = "TEXT")
    private String context;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
