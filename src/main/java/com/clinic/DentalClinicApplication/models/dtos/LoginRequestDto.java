package com.clinic.DentalClinicApplication.models.dtos;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String username;
    private String password;
}
