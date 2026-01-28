package com.clinic.DentalClinicApplication.services;

import com.clinic.DentalClinicApplication.exceptions.EmailAlreadyExistsException;
import com.clinic.DentalClinicApplication.exceptions.InvalidCredentialsException;
import com.clinic.DentalClinicApplication.exceptions.UserAlreadyExistsException;
import com.clinic.DentalClinicApplication.models.dtos.LoginRequestDto;
import com.clinic.DentalClinicApplication.models.dtos.LoginResponseDto;
import com.clinic.DentalClinicApplication.models.dtos.SignupRequestDto;
import com.clinic.DentalClinicApplication.models.dtos.SignupResponseDto;
import com.clinic.DentalClinicApplication.models.entities.User;
import com.clinic.DentalClinicApplication.models.enums.UserRole;
import com.clinic.DentalClinicApplication.repositories.UserRepository;
import com.clinic.DentalClinicApplication.utils.AuthUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthSvcImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;

    @Override
    @Transactional
    public SignupResponseDto signup(SignupRequestDto signupRequestDto) {

        log.info("Attempting to register user: {}", signupRequestDto.getUsername());


        if (userRepository.existsByUsername(signupRequestDto.getUsername())) {
            throw new UserAlreadyExistsException(
                    "Username '" + signupRequestDto.getUsername() + "' is already taken"
            );
        }

        if (userRepository.existsByEmail(signupRequestDto.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "Email '" + signupRequestDto.getEmail() + "' is already registered"
            );
        }

        // Create and save user
        User user = User.builder()
                .fullName(signupRequestDto.getUsername())
                .email(signupRequestDto.getEmail())
                .phone(signupRequestDto.getPhoneNumber())
                .password(passwordEncoder.encode(signupRequestDto.getPassword()))
                .role(UserRole.valueOf("PATIENT"))
                .createdAt(LocalDateTime.now())
                .build();

        User savedUser = userRepository.save(user);

        log.info("User registered successfully: {}", savedUser.getUsername());

        return SignupResponseDto.builder()
                .userId(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .phoneNumber(savedUser.getPhone())
                .role(String.valueOf(savedUser.getRole()))
                .status(String.valueOf(savedUser.getActive()))
                .message("User registered successfully")
                .registeredAt(savedUser.getCreatedAt())
                .build();
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDto.getUsername(),
                            loginRequestDto.getPassword()
                    )
            );

            User user = (User) authentication.getPrincipal();
            String token = authUtil.generateAccessToken(user);

            log.info("User logged in successfully: {}", user.getUsername());

            return new LoginResponseDto(token, user.getId());

        } catch (BadCredentialsException e) {
            log.error("Login failed - invalid credentials for user: {}", loginRequestDto.getUsername());
            throw new InvalidCredentialsException("Invalid username or password");
        }
    }
}