package io.github.jthamayo.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.jthamayo.backend.dto.AddressDto;
import io.github.jthamayo.backend.dto.JobDto;
import io.github.jthamayo.backend.dto.JobWithAddressDto;
import io.github.jthamayo.backend.dto.UserDto;
import io.github.jthamayo.backend.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
	this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
	UserDto savedUser = userService.createUser(userDto);
	return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
	UserDto userDto = userService.getUserById(userId);
	return ResponseEntity.ok(userDto);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
	List<UserDto> usersDto = userService.getAllUsers();
	return ResponseEntity.ok(usersDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @RequestBody UserDto updatedUser) {
	UserDto userDto = userService.updateUser(userId, updatedUser);
	return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
	userService.deleteUser(userId);
	return ResponseEntity.ok("The user has been successfully deleted");
    }

    @PostMapping("{id}/profile/address")
    public ResponseEntity<UserDto> addHomeAddress(@PathVariable("id") Long userId, @RequestBody AddressDto addressDto) {
	UserDto userDto = userService.addHomeAddress(userId, addressDto);
	return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PostMapping("{id}/profile/jobs")
    public ResponseEntity<UserDto> addJob(@PathVariable("id") Long userId, @RequestBody JobWithAddressDto job) {
	UserDto userDto = userService.addJob(userId, job.getJob(), job.getAddress());
	return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @GetMapping("{id}/profile/address")
    public ResponseEntity<AddressDto> getHomeAddress(@PathVariable("id") Long userId) {
	AddressDto addressDto = userService.getHomeAddress(userId);
	return ResponseEntity.ok(addressDto);
    }

    @GetMapping("{id}/profile/jobs")
    public ResponseEntity<List<JobDto>> getJobs(@PathVariable("id") Long userId) {
	List<JobDto> jobs = userService.getJobs(userId);
	return ResponseEntity.ok(jobs);
    }

}
