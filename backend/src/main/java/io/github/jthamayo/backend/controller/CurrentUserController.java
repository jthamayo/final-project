package io.github.jthamayo.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.jthamayo.backend.dto.AddressDto;
import io.github.jthamayo.backend.dto.JobDto;
import io.github.jthamayo.backend.dto.UserDto;
import io.github.jthamayo.backend.dto.UserProfileDto;
import io.github.jthamayo.backend.dto.UserSummary;
import io.github.jthamayo.backend.dto.VehicleDto;
import io.github.jthamayo.backend.security.UserPrincipal;
import io.github.jthamayo.backend.service.UserService;

@RestController
@RequestMapping("/api/user")
public class CurrentUserController {

    private UserService userService;

    public CurrentUserController(UserService userService) {
	this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserSummary> getCurrentUser(@AuthenticationPrincipal UserPrincipal currentUser) {
	UserSummary user = new UserSummary(currentUser.getId(), currentUser.getFirstName(), currentUser.getLastName(),
		currentUser.getUsername(), currentUser.getEmail());
	return ResponseEntity.ok(user);
    }

    @GetMapping("/me/jobs")
    public ResponseEntity<List<JobDto>> getCurrentUserJobs(@AuthenticationPrincipal UserPrincipal currentUser) {
	List<JobDto> jobs = userService.getJobs(currentUser.getId());
	return ResponseEntity.ok(jobs);
    }

    @GetMapping("/me/vehicles")
    public ResponseEntity<VehicleDto> getCurrentUserVehicles(@AuthenticationPrincipal UserPrincipal currentUser) {
	VehicleDto vehicle = userService.getVehicle(currentUser.getId());
	return ResponseEntity.ok(vehicle);
    }

    @GetMapping("/me/address")
    public ResponseEntity<AddressDto> getCurrentUserAddress(@AuthenticationPrincipal UserPrincipal currentUser) {
	AddressDto addressDto = userService.getHomeAddress(currentUser.getId());
	return ResponseEntity.ok(addressDto);
    }

    @GetMapping("/me/profile")
    public ResponseEntity<UserProfileDto> getCurrentUserProfile(@AuthenticationPrincipal UserPrincipal currentUser) {
	Long userId = currentUser.getId();
	List<JobDto> jobs = userService.getJobs(userId);
	AddressDto address = userService.getHomeAddress(userId);
	VehicleDto vehicle = userService.getVehicle(userId);
	UserDto user = userService.getUserById(userId);
	UserProfileDto profile = new UserProfileDto();
	return ResponseEntity.ok(profile);

    }
}
