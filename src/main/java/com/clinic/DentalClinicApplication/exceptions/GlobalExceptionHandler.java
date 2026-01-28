package com.clinic.DentalClinicApplication.exceptions;

import com.clinic.DentalClinicApplication.models.dtos.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // Handle EmailAlreadyExistsException
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Object>> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
        log.error("Email already exists: {}", ex.getMessage());

        ApiResponse<Object> response = ApiResponse.builder()
                .success(false)
                .message(ex.getMessage())
                .error("EMAIL_ALREADY_EXISTS")
                .statusCode(HttpStatus.CONFLICT.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    // Handle UserAlreadyExistsException
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Object>> handleUserAlreadyExists(UserAlreadyExistsException ex) {
        log.error("User already exists: {}", ex.getMessage());

        ApiResponse<Object> response = ApiResponse.builder()
                .success(false)
                .message(ex.getMessage())
                .error("USER_ALREADY_EXISTS")
                .statusCode(HttpStatus.CONFLICT.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    // Handle UserNotFoundException
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleUserNotFound(UserNotFoundException ex) {
        log.error("User not found: {}", ex.getMessage());

        ApiResponse<Object> response = ApiResponse.builder()
                .success(false)
                .message(ex.getMessage())
                .error("USER_NOT_FOUND")
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Handle BadCredentialsException (from Spring Security)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<Object>> handleBadCredentials(BadCredentialsException ex) {
        log.error("Invalid credentials: {}", ex.getMessage());

        ApiResponse<Object> response = ApiResponse.builder()
                .success(false)
                .message("Invalid username or password")
                .error("INVALID_CREDENTIALS")
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    // Handle Validation Errors (@Valid in controller)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        log.error("Validation errors: {}", errors);

        ApiResponse<Map<String, String>> response = ApiResponse.<Map<String, String>>builder()
                .success(false)
                .message("Validation failed")
                .data(errors)
                .error("VALIDATION_ERROR")
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Handle IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Object>> handleIllegalArgument(IllegalArgumentException ex) {
        log.error("Illegal argument: {}", ex.getMessage());

        ApiResponse<Object> response = ApiResponse.builder()
                .success(false)
                .message(ex.getMessage())
                .error("BAD_REQUEST")
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Handle all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGlobalException(Exception ex) {
        log.error("Unexpected error occurred: {}", ex.getMessage(), ex);

        ApiResponse<Object> response = ApiResponse.builder()
                .success(false)
                .message("An unexpected error occurred")
                .error("INTERNAL_SERVER_ERROR")
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}