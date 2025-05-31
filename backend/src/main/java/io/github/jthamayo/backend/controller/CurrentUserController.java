package io.github.jthamayo.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.jthamayo.backend.dto.UserSummary;
import io.github.jthamayo.backend.security.UserPrincipal;

@RestController
@RequestMapping("/api/user")
public class CurrentUserController {

    @GetMapping("/me")
    public ResponseEntity<UserSummary> getCurrentUser(@AuthenticationPrincipal UserPrincipal currentUser) {
	UserSummary user = new UserSummary(currentUser.getFirstName(), currentUser.getLastName(),
		currentUser.getUsername(), currentUser.getEmail());
	return ResponseEntity.ok(user);
    }
}
