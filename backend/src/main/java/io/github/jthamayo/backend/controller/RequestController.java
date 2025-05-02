package io.github.jthamayo.backend.controller;

import java.time.LocalDate;
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

import io.github.jthamayo.backend.dto.RequestDto;
import io.github.jthamayo.backend.service.RequestService;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    private RequestService requestService;

    public RequestController(RequestService requestService) {
	this.requestService = requestService;
    }

    @PostMapping
    public ResponseEntity<RequestDto> createRequest(@RequestBody RequestDto requestDto) {
	RequestDto savedRequest = requestService.createRequest(requestDto);
	return new ResponseEntity<>(savedRequest, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<RequestDto> getRequest(@PathVariable("id") Long requestId) {
	RequestDto requestDto = requestService.getRequestById(requestId);
	return ResponseEntity.ok(requestDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRequest(@PathVariable("id") Long requestId) {
	requestService.deleteRequest(requestId);
	return ResponseEntity.ok("The request has been successfully deleted");
    }

    @PutMapping("{id}")
    public ResponseEntity<RequestDto> updateRequest(@PathVariable("id") Long requestId,
	    @RequestBody RequestDto updatedRequest) {
	updatedRequest.setSentDate(LocalDate.now());
	RequestDto request = requestService.updateRequest(requestId, updatedRequest);
	return ResponseEntity.ok(request);
    }

    @GetMapping
    public ResponseEntity<List<RequestDto>> getAllRequests() {
	List<RequestDto> requests = requestService.getAllRequests();
	return ResponseEntity.ok(requests);
    }

    @PostMapping("user/{senderId}/to/{receiverId}")
    public ResponseEntity<RequestDto> sendUserRequest(@PathVariable Long senderId, @PathVariable Long receiverId) {
	RequestDto request = requestService.sendUserRequest(senderId, receiverId);
	return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

    @GetMapping("user/{id}/pending/received")
    public ResponseEntity<List<RequestDto>> getPendingReceivedRequests(@PathVariable("id") Long userId) {
	List<RequestDto> requests = requestService.getPendingReceivedRequests(userId);
	return ResponseEntity.ok(requests);
    }

    @GetMapping("user/{id}/pending/sent")
    public ResponseEntity<List<RequestDto>> getPendingSentRequests(@PathVariable("id") Long userId) {
	List<RequestDto> requests = requestService.getPendingSentRequests(userId);
	return ResponseEntity.ok(requests);
    }

    @PutMapping("user/{userId}/reject/{requestId}")
    public ResponseEntity<RequestDto> rejectRequest(@PathVariable Long userId, @PathVariable Long requestId) {
	RequestDto request = requestService.rejectRequest(userId, requestId);
	return ResponseEntity.ok(request);
    }

    @PutMapping("user/{userId}/accept/{requestId}")
    public ResponseEntity<RequestDto> acceptRequest(@PathVariable Long userId, @PathVariable Long requestId) {
	RequestDto request = requestService.acceptRequest(userId, requestId);
	return ResponseEntity.ok(request);
    }

}
