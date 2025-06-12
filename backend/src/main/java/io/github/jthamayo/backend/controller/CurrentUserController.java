package io.github.jthamayo.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.github.jthamayo.backend.dto.AddressDto;
import io.github.jthamayo.backend.dto.JobDto;
import io.github.jthamayo.backend.dto.RequestDto;
import io.github.jthamayo.backend.dto.UserDto;
import io.github.jthamayo.backend.dto.UserProfileDto;
import io.github.jthamayo.backend.dto.UserSummary;
import io.github.jthamayo.backend.dto.VehicleDto;
import io.github.jthamayo.backend.entity.User;
import io.github.jthamayo.backend.exception.BadRequestException;
import io.github.jthamayo.backend.security.UserPrincipal;
import io.github.jthamayo.backend.service.NetworkService;
import io.github.jthamayo.backend.service.RequestService;
import io.github.jthamayo.backend.service.UserService;

@RestController
@RequestMapping("/api/user")
public class CurrentUserController {

    private UserService userService;
    private NetworkService networkService;
    private RequestService requestService;

    public CurrentUserController(UserService userService, NetworkService networkService,
	    RequestService requestService) {
	this.userService = userService;
	this.networkService = networkService;
	this.requestService = requestService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserSummary> getCurrentUser(@AuthenticationPrincipal UserPrincipal currentUser) {
	UserSummary user = new UserSummary(currentUser.getId(), currentUser.getFirstName(), currentUser.getLastName(),
		currentUser.getUsername(), currentUser.getEmail());
	user.setProfilePictureUrl(userService.getProfilePicture(currentUser.getId()));
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
	UserProfileDto profile = userService.getProfile(currentUser.getId());
	return ResponseEntity.ok(profile);

    }

    @PutMapping("/me/profile/edit")
    public ResponseEntity<UserDto> editCurrentUserProfile(@AuthenticationPrincipal UserPrincipal currentUser,
	    @RequestParam UserDto updatedUser) {
	UserDto user = userService.updateUser(currentUser.getId(), updatedUser);
	return ResponseEntity.ok(user);
    }

    @PostMapping("/me/profile/picture")
    public ResponseEntity<Map<String, String>> uploadProfilePicture(@AuthenticationPrincipal UserPrincipal currentUser,
	    @RequestParam MultipartFile file) {
	try {
	    String url = userService.uploadProfilePicture(currentUser.getId(), file);
	    return ResponseEntity.ok(Map.of("url", url));
	} catch (BadRequestException e) {
	    return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
	} catch (Exception e) {
	    return ResponseEntity.internalServerError().body(Map.of("error", "Unexpected error"));
	}
    }

    @GetMapping("/me/search")
    public ResponseEntity<List<UserSummary>> searchUsers(@AuthenticationPrincipal UserPrincipal currentUser) {
	List<UserSummary> users = networkService.getUnconnectedUsers(currentUser.getId());
	return ResponseEntity.ok(users);
    }

    @PostMapping("/connect/{username}")
    public ResponseEntity<RequestDto> sendUserRequest(@AuthenticationPrincipal UserPrincipal currentUser,
	    @PathVariable String username) {
	UserDto user = userService.getUserByUsername(username);
	RequestDto request = requestService.sendUserRequest(currentUser.getId(), user.getId());
	return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

    @GetMapping("requests/pending")
    public ResponseEntity<List<RequestDto>> getPendingRequests(@AuthenticationPrincipal UserPrincipal currentUser) {
	List<RequestDto> requests = requestService.getPendingReceivedRequests(currentUser.getId());
	return ResponseEntity.ok(requests);
    }
}
