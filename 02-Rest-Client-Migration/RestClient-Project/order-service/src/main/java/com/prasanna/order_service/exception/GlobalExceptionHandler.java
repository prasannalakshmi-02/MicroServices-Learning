package com.prasanna.order_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;

import java.time.LocalDateTime;
import com.prasanna.order_service.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<ErrorResponse> handleResourceAccessException(ResourceAccessException ex) {

        ErrorResponse error = new ErrorResponse(
                "Sorry, the Product System is currently down. Please try again later.",
                "503",
                LocalDateTime.now()
        );

        return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);

    }
}
