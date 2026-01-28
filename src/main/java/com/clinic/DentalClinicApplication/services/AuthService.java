package com.clinic.DentalClinicApplication.services;

import com.clinic.DentalClinicApplication.models.dtos.LoginRequestDto;
import com.clinic.DentalClinicApplication.models.dtos.LoginResponseDto;
import com.clinic.DentalClinicApplication.models.dtos.SignupRequestDto;
import com.clinic.DentalClinicApplication.models.dtos.SignupResponseDto;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto loginRequestDto);

    SignupResponseDto signup(SignupRequestDto signupRequestDto);
}
