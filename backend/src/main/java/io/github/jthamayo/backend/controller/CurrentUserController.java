package io.github.jthamayo.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import io.github.jthamayo.backend.service.impl.JobServiceImpl;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.github.jthamayo.backend.dto.AddressDto;
import io.github.jthamayo.backend.dto.DependentDto;
import io.github.jthamayo.backend.dto.GroupDto;
import io.github.jthamayo.backend.dto.GroupParticipantsDto;
import io.github.jthamayo.backend.dto.JobDto;
import io.github.jthamayo.backend.dto.JobWithAddressDto;
import io.github.jthamayo.backend.dto.NetworkDto;
import io.github.jthamayo.backend.dto.RequestDto;
import io.github.jthamayo.backend.dto.RequestDetailsDto;
import io.github.jthamayo.backend.dto.UserDto;
import io.github.jthamayo.backend.dto.UserProfileDto;
import io.github.jthamayo.backend.dto.UserSummary;
import io.github.jthamayo.backend.dto.VehicleDto;
import io.github.jthamayo.backend.exception.BadRequestException;
import io.github.jthamayo.backend.security.UserPrincipal;
import io.github.jthamayo.backend.service.AddressService;
import io.github.jthamayo.backend.service.DependentService;
import io.github.jthamayo.backend.service.GroupService;
import io.github.jthamayo.backend.service.JobService;
import io.github.jthamayo.backend.service.NetworkService;
import io.github.jthamayo.backend.service.RequestService;
import io.github.jthamayo.backend.service.UserService;
import io.github.jthamayo.backend.service.VehicleService;

@RestController
@RequestMapping("/api/user")
public class CurrentUserController {

    private UserService userService;
    private NetworkService networkService;
    private RequestService requestService;
    private AddressService addressService;
    private DependentService dependentService;
    private JobService jobService;
    private VehicleService vehicleService;
    private GroupService groupService;

    public CurrentUserController(UserService userService, NetworkService networkService, RequestService requestService,
	    AddressService addressService, DependentService dependentService, JobService jobService,
	    VehicleService vehicleService, GroupService groupService) {
	this.userService = userService;
	this.networkService = networkService;
	this.requestService = requestService;
	this.addressService = addressService;
	this.dependentService = dependentService;
	this.jobService = jobService;
	this.vehicleService = vehicleService;
	this.groupService = groupService;
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

    @GetMapping("requests/sent/pending")
    public ResponseEntity<List<RequestDetailsDto>> getSentPendingRequests(
	    @AuthenticationPrincipal UserPrincipal currentUser) {
	List<RequestDetailsDto> requests = requestService.getPendingSentRequests(currentUser.getId());
	return ResponseEntity.ok(requests);
    }

    @GetMapping("requests/received/pending")
    public ResponseEntity<List<RequestDetailsDto>> getReceivedPendingRequests(
	    @AuthenticationPrincipal UserPrincipal currentUser) {
	List<RequestDetailsDto> requests = requestService.getPendingReceivedRequests(currentUser.getId());
	return ResponseEntity.ok(requests);
    }

    @PostMapping("requests/accept/{requestId}")
    public ResponseEntity<NetworkDto> acceptRequest(@AuthenticationPrincipal UserPrincipal currentUser,
	    @PathVariable Long requestId) {
	RequestDto request = requestService.acceptRequest(currentUser.getId(), requestId);
	NetworkDto network = networkService.createNetwork(new NetworkDto(currentUser.getId(), request.getUserSender()));
	return new ResponseEntity<>(network, HttpStatus.CREATED);
    }

    @PutMapping("requests/reject/{requestId}")
    public ResponseEntity<RequestDto> rejectRequest(@AuthenticationPrincipal UserPrincipal currentUser,
	    @PathVariable Long requestId) {
	RequestDto request = requestService.rejectRequest(currentUser.getId(), requestId);
	return ResponseEntity.ok(request);
    }

    @GetMapping("/connections")
    public ResponseEntity<List<UserSummary>> getUserConnections(@AuthenticationPrincipal UserPrincipal currentUser) {
	List<UserSummary> networks = networkService.getUserConnections(currentUser.getId());
	return ResponseEntity.ok(networks);
    }

    @GetMapping("/group/candidates")
    public ResponseEntity<List<UserSummary>> getUngroupedUserConnections(
	    @AuthenticationPrincipal UserPrincipal currentUser) {
	List<UserSummary> networks = networkService.getUngroupedUserConnections(currentUser.getId());
	return ResponseEntity.ok(networks);
    }

    @PostMapping("/add/group")
    public ResponseEntity<GroupDto> addGroup(@AuthenticationPrincipal UserPrincipal currentUser,
	    @RequestBody List<String> usernames) {
	List<String> participants = new ArrayList<>(usernames);
	participants.add(currentUser.getUsername());
	GroupDto group = groupService.createGroupFromUsername(participants);
	return ResponseEntity.ok(group);
    }

    @PostMapping("/add/address")
    public ResponseEntity<AddressDto> addAddress(@AuthenticationPrincipal UserPrincipal currentUser,
	    @RequestBody AddressDto addressDto) {
	AddressDto address = addressService.createAddress(addressDto);
	userService.addHomeAddress(currentUser.getId(), address);
	return new ResponseEntity<>(address, HttpStatus.CREATED);
    }

    @PostMapping("/add/job")
    public ResponseEntity<JobDto> addJob(@AuthenticationPrincipal UserPrincipal currentUser,
	    @RequestBody JobWithAddressDto jobWithAddressDto) {
	JobDto jobDto = jobWithAddressDto.getJob();
	AddressDto address = addressService.createAddress(jobWithAddressDto.getAddress());
	jobDto.setAddressId(address.getId());
	jobDto.setUserId(currentUser.getId());
	JobDto job = jobService.createJob(jobDto);
	userService.addJob(currentUser.getId(), job, address);
	return new ResponseEntity<>(job, HttpStatus.CREATED);
    }

    @PostMapping("/add/dependents")
    public ResponseEntity<DependentDto> addDependent(@AuthenticationPrincipal UserPrincipal currentUser,
	    @RequestBody DependentDto dependentDto) {
	dependentDto.setGuardianId(currentUser.getId());
	DependentDto dependent = dependentService.createDependent(dependentDto);
	userService.addDependent(currentUser.getId(), dependent);
	return new ResponseEntity<>(dependent, HttpStatus.CREATED);
    }

    @PostMapping("/add/vehicle")
    public ResponseEntity<VehicleDto> addVehicle(@AuthenticationPrincipal UserPrincipal currentUser,
	    @RequestBody VehicleDto vehicleDto) {
	VehicleDto vehicle = vehicleService.createVehicle(vehicleDto);
	userService.addVehicle(currentUser.getId(), vehicle);
	return new ResponseEntity<>(vehicle, HttpStatus.CREATED);
    }

    @GetMapping("/group")
    public ResponseEntity<GroupParticipantsDto> getGroup(@AuthenticationPrincipal UserPrincipal currentUser) {
	GroupParticipantsDto group = userService.getUserGroupParticipants(currentUser.getId());
	return ResponseEntity.ok(group);
    }

    @PostMapping("/group/add/{username}")
    public ResponseEntity<GroupParticipantsDto> addUserToGroup(@AuthenticationPrincipal UserPrincipal currentUser,
	    @PathVariable String username) {
	GroupParticipantsDto group = userService.addUserToGroup(currentUser.getId(), username);
	return ResponseEntity.ok(group);
    }
}
