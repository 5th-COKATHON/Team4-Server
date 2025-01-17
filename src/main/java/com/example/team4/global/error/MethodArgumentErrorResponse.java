package com.example.team4.global.error;

import jakarta.servlet.http.HttpServletRequest;

public record MethodArgumentErrorResponse(
        String message,
        String method,
        String requestURI
) {

    public static MethodArgumentErrorResponse of(com.example.team4.global.error.ErrorCode errorCode, HttpServletRequest request) {
        return new MethodArgumentErrorResponse(
                errorCode.getMessage(),
                request.getMethod(),
                request.getRequestURI()
        );
    }
}
