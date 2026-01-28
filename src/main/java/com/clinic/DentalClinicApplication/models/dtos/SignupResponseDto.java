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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignupResponseDto {

    private Long userId;
    private String username;
    private String email;
    private String phoneNumber;
    private String role;
    private String status;
    private String message;
    private LocalDateTime registeredAt;

    public void getSetRegisteredAt(LocalDateTime createdAt) {
        LocalDateTime.now();
    }

}