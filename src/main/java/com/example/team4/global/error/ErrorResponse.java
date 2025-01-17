package com.example.team4.global.error;

import jakarta.servlet.http.HttpServletRequest;

public record ErrorResponse(
        String message,
        String method,
        String requestURI
) {
    public static ErrorResponse of(ErrorCode errorCode, HttpServletRequest request) {
        return new ErrorResponse(
                errorCode.getMessage(),
                request.getMethod(),
                request.getRequestURI()
        );
    }

    public static ErrorResponse of(HttpServletRequest request, ErrorCode errorCode, final String errorMessage) {
        return new ErrorResponse(
                errorMessage,
                request.getMethod(),
                request.getRequestURI()
        );
    }
}
