package com.clinic.DentalClinicApplication.services;

import com.clinic.DentalClinicApplication.models.dtos.ChangePasswordRequestDto;
import com.clinic.DentalClinicApplication.models.dtos.UpdateUserRequestDto;
import com.clinic.DentalClinicApplication.models.dtos.UserDto;

import java.util.List;

public interface UserSvc {

    UserDto getCurrentUser();

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers(int page, int size);

    UserDto updateCurrentUser(UpdateUserRequestDto updateRequest);

    UserDto updateUser(Long userId, UpdateUserRequestDto updateRequest);

    void deleteUser(Long userId);

    void deactivateCurrentUser();

    void changePassword(ChangePasswordRequestDto changePasswordRequest);

    List<UserDto> searchUsers(String username, String email, String phone);

    UserDto updateUserRole(Long userId, String role);

    UserDto updateUserStatus(Long userId, String status);
}