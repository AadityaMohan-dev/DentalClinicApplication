package com.clinic.DentalClinicApplication.models.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)  // Only include non-null fields in JSON response
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private String error;
    private Integer statusCode;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    // ========== Static Factory Methods for Success Responses ==========

    /**
     * Success response with data and message
     */
    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Success response with only data
     */
    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .message("Operation successful")
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Success response with only message (no data)
     */
    public static <T> ApiResponse<T> success(String message) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }

    // ========== Static Factory Methods for Error Responses ==========

    /**
     * Error response with error type, message, and status code
     */
    public static <T> ApiResponse<T> error(String error, String message, Integer statusCode) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .error(error)
                .statusCode(statusCode)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Error response with only message
     */
    public static <T> ApiResponse<T> error(String message) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .error("ERROR")
                .statusCode(500)
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Error response with message and status code
     */
    public static <T> ApiResponse<T> error(String message, Integer statusCode) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .error("ERROR")
                .statusCode(statusCode)
                .timestamp(LocalDateTime.now())
                .build();
    }
}