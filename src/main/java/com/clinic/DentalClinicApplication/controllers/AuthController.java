package com.clinic.DentalClinicApplication.controllers;

import com.clinic.DentalClinicApplication.models.dtos.LoginRequestDto;
import com.clinic.DentalClinicApplication.models.dtos.LoginResponseDto;
import com.clinic.DentalClinicApplication.models.dtos.SignupRequestDto;
import com.clinic.DentalClinicApplication.models.dtos.SignupResponseDto;
import com.clinic.DentalClinicApplication.services.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody SignupRequestDto signupRequestDto){
        return ResponseEntity.ok(authService.signup(signupRequestDto));
    }
}
