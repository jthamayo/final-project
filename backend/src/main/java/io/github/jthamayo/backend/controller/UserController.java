package io.github.jthamayo.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.jthamayo.backend.dto.UserDto;
import io.github.jthamayo.backend.service.UserService;

@RestController
@RequestMapping("/api/user")
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
}
