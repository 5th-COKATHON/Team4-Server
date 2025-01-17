package com.example.team4.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 게시물이 없습니다"),
    ;
    private final HttpStatus httpStatus;
    private final String message;


}
