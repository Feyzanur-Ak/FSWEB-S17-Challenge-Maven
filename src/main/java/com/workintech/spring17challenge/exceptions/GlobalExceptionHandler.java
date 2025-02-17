package com.workintech.spring17challenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Özel ApiException için handler
    @ExceptionHandler
    private ResponseEntity<ApiErrorResponse> handleApiException(ApiException apiException) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                apiException.getStatus().value(),
                apiException.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, apiException.getStatus());
    }

    // Genel Exception için handler
    @ExceptionHandler
    private ResponseEntity<ApiErrorResponse> handleGeneralException(Exception exception) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), // 500 hatası döndürülür
                "An unexpected error occurred: " + exception.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
