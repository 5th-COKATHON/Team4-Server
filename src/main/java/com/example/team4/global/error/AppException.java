package com.example.team4.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AppException extends RuntimeException {

    private ErrorCode errorCode;
}
