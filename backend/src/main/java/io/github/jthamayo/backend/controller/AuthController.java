package io.github.jthamayo.backend.controller;

import java.net.URI;
import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.jthamayo.backend.payload.JwtAuthenticationResponse;
import io.github.jthamayo.backend.payload.LoginRequest;
import io.github.jthamayo.backend.payload.SignUpRequest;
import io.github.jthamayo.backend.entity.Role;
import io.github.jthamayo.backend.entity.User;
import io.github.jthamayo.backend.entity.enums.RoleType;
import io.github.jthamayo.backend.exception.AppException;
import io.github.jthamayo.backend.payload.ApiResponse;
import io.github.jthamayo.backend.repository.RoleRepository;
import io.github.jthamayo.backend.repository.UserRepository;
import io.github.jthamayo.backend.security.JwtTokenProvider;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    AuthenticationManager authenticationManager;
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
	    RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
	this.authenticationManager = authenticationManager;
	this.userRepository = userRepository;
	this.roleRepository = roleRepository;
	this.passwordEncoder = passwordEncoder;
	this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
	Authentication authentication = authenticationManager.authenticate(
		new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));
	SecurityContextHolder.getContext().setAuthentication(authentication);
	String jwt = jwtTokenProvider.generateToken(authentication);
	return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

	if (userRepository.existsByUsername(signUpRequest.getUsername())) {
	    return new ResponseEntity<>(new ApiResponse(false, "Username is taken"), HttpStatus.BAD_REQUEST);
	}
	if (userRepository.existsByEmail(signUpRequest.getEmail())) {
	    return new ResponseEntity<>(new ApiResponse(false, "Email address already in use"), HttpStatus.BAD_REQUEST);
	}
	User user = new User(signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getUsername(),
		signUpRequest.getEmail(), signUpRequest.getPhoneNumber());
	user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

	Role userRoles = roleRepository.findByName(RoleType.USER)
		.orElseThrow(() -> new AppException("Role User not in th db"));

	user.setRoles(Collections.singleton(userRoles));

	User signedUpUser = userRepository.save(user);

	URI redirect = ServletUriComponentsBuilder.fromCurrentContextPath().path("api/users/{username}")
		.buildAndExpand(signedUpUser.getUsername()).toUri();

	return ResponseEntity.created(redirect).body(new ApiResponse(true, "User registered successfully"));

    }
}
