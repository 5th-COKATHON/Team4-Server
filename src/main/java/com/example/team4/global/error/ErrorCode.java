package com.example.team4.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "잘못된 입력 값입니다"),

    // Member
    MEMBER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 존재하는 회원입니다"),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 회원을 찾을 수 없습니다"),
    DUPLICATED_EMAIL(HttpStatus.BAD_REQUEST, "이미 사용중인 이메일입니다"),
    DUPLICATED_NICKNAME(HttpStatus.BAD_REQUEST, "이미 사용중인 닉네임입니다"),

    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 게시물이 없습니다"),

    IMAGE_PROCESSING_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "컨버트 에러"),
    ;
    private final HttpStatus httpStatus;
    private final String message;


}
