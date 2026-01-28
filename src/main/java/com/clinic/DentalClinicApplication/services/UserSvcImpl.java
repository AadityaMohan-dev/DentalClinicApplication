package com.clinic.DentalClinicApplication.services;

import com.clinic.DentalClinicApplication.models.dtos.ChangePasswordRequestDto;
import com.clinic.DentalClinicApplication.models.dtos.UpdateUserRequestDto;
import com.clinic.DentalClinicApplication.models.dtos.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSvcImpl implements UserSvc{
    @Override
    public UserDto getCurrentUser() {
        return null;
    }

    @Override
    public UserDto getUserById(Long userId) {
        return null;
    }

    @Override
    public List<UserDto> getAllUsers(int page, int size) {
        return List.of();
    }

    @Override
    public UserDto updateCurrentUser(UpdateUserRequestDto updateRequest) {
        return null;
    }

    @Override
    public UserDto updateUser(Long userId, UpdateUserRequestDto updateRequest) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public void deactivateCurrentUser() {

    }

    @Override
    public void changePassword(ChangePasswordRequestDto changePasswordRequest) {

    }

    @Override
    public List<UserDto> searchUsers(String username, String email, String phone) {
        return List.of();
    }

    @Override
    public UserDto updateUserRole(Long userId, String role) {
        return null;
    }

    @Override
    public UserDto updateUserStatus(Long userId, String status) {
        return null;
    }
}
