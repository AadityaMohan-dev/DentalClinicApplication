package com.clinic.DentalClinicApplication.controllers;

import com.clinic.DentalClinicApplication.models.dtos.ApiResponse;
import com.clinic.DentalClinicApplication.models.dtos.ChangePasswordRequestDto;
import com.clinic.DentalClinicApplication.models.dtos.UpdateUserRequestDto;
import com.clinic.DentalClinicApplication.models.dtos.UserDto;
import com.clinic.DentalClinicApplication.services.UserSvc;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserSvc userSvc;

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<UserDto>> getCurrentUserProfile() {
        UserDto user = userSvc.getCurrentUser();

        ApiResponse<UserDto> response = ApiResponse.success(
                user,
                "User profile retrieved successfully"
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")  // Only admin or doctor can view other users
    public ResponseEntity<ApiResponse<UserDto>> getUserById(@PathVariable Long userId) {
        UserDto user = userSvc.getUserById(userId);

        ApiResponse<UserDto> response = ApiResponse.success(
                user,
                "User retrieved successfully"
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        List<UserDto> users = userSvc.getAllUsers(page, size);

        ApiResponse<List<UserDto>> response = ApiResponse.success(
                users,
                "Users retrieved successfully"
        );

        return ResponseEntity.ok(response);
    }


    @PutMapping("/profile")
    public ResponseEntity<ApiResponse<UserDto>> updateCurrentUserProfile(
            @Valid @RequestBody UpdateUserRequestDto updateRequest) {

        UserDto updatedUser = userSvc.updateCurrentUser(updateRequest);

        ApiResponse<UserDto> response = ApiResponse.success(
                updatedUser,
                "Profile updated successfully"
        );

        return ResponseEntity.ok(response);
    }


    @PutMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<UserDto>> updateUser(
            @PathVariable Long userId,
            @Valid @RequestBody UpdateUserRequestDto updateRequest) {

        UserDto updatedUser = userSvc.updateUser(userId, updateRequest);

        ApiResponse<UserDto> response = ApiResponse.success(
                updatedUser,
                "User updated successfully"
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long userId) {
        userSvc.deleteUser(userId);

        ApiResponse<Void> response = ApiResponse.success("User deleted successfully");

        return ResponseEntity.ok(response);
    }


    @PostMapping("/deactivate")
    public ResponseEntity<ApiResponse<Void>> deactivateAccount() {
        userSvc.deactivateCurrentUser();

        ApiResponse<Void> response = ApiResponse.success("Account deactivated successfully");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/change-password")
    public ResponseEntity<ApiResponse<Void>> changePassword(
            @Valid @RequestBody ChangePasswordRequestDto changePasswordRequest) {

        userSvc.changePassword(changePasswordRequest);

        ApiResponse<Void> response = ApiResponse.success("Password changed successfully");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<ApiResponse<List<UserDto>>> searchUsers(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone) {

        List<UserDto> users = userSvc.searchUsers(username, email, phone);

        ApiResponse<List<UserDto>> response = ApiResponse.success(
                users,
                "Search completed successfully"
        );

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{userId}/role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<UserDto>> updateUserRole(
            @PathVariable Long userId,
            @RequestParam String role) {

        UserDto updatedUser = userSvc.updateUserRole(userId, role);

        ApiResponse<UserDto> response = ApiResponse.success(
                updatedUser,
                "User role updated successfully"
        );

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{userId}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<UserDto>> updateUserStatus(
            @PathVariable Long userId,
            @RequestParam String status) {

        UserDto updatedUser = userSvc.updateUserStatus(userId, status);

        ApiResponse<UserDto> response = ApiResponse.success(
                updatedUser,
                "User status updated successfully"
        );

        return ResponseEntity.ok(response);
    }
}