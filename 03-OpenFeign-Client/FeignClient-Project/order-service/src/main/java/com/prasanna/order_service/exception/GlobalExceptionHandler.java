package com.prasanna.order_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import feign.RetryableException;
import feign.FeignException;

import java.time.LocalDateTime;
import com.prasanna.order_service.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RetryableException.class)
    public ResponseEntity<ErrorResponse> handleFeignConnectionError(RetryableException ex) {

        ErrorResponse error = new ErrorResponse();
        error.setStatusCode("503");
        error.setMessage("External Service is unavailable. Please try again later.");
        error.setTimeStamp(LocalDateTime.now().toString()); // Make sure you have LocalDateTime imported

        return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
    }

    // Optional: Catch 404/500 errors returned BY the other service
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponse> handleFeignStatusError(FeignException ex) {

        ErrorResponse error = new ErrorResponse();
        error.setStatusCode(String.valueOf(ex.status()));
        error.setMessage("Error from external service: " + ex.getMessage());
        error.setTimeStamp(LocalDateTime.now().toString());

        return new ResponseEntity<>(error, HttpStatus.valueOf(ex.status()));
    }
}
