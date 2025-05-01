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
	return ResponseEntity.ok("The request has ben successfully deleted");
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

}
