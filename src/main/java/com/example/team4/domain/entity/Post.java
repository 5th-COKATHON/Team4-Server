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

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "place_name")
    private String placeName;

    @Column(name = "context", columnDefinition = "TEXT")
    private String context;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    private Post(Emotion emotion, LocalDate date, Double latitude, Double longitude, String placeName, String context,
                 Member member) {
        this.emotion = emotion;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.placeName = placeName;
        this.context = context;
        this.member = member;
    }

    public static Post of(Member member, Emotion emotion, LocalDate date, String placeName, Double latitude,
                          Double longitude, String context) {
        return new Post(emotion, date, latitude, longitude, placeName, context, member);
    }
}
