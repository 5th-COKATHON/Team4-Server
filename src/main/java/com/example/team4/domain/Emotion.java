package com.example.team4.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Emotion {

    HAPPY("기쁨"),
    SAD("슬픔"),
    ANGRY("분노"),
    ;

    private final String description;
}
