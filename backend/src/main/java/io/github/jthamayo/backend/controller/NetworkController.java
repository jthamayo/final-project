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

import io.github.jthamayo.backend.dto.NetworkDto;
import io.github.jthamayo.backend.dto.UserDto;
import io.github.jthamayo.backend.service.NetworkService;

@RestController
@RequestMapping("api/networks")
public class NetworkController {

    private NetworkService networkService;

    public NetworkController(NetworkService networkService) {
	this.networkService = networkService;
    }

    @PostMapping
    public ResponseEntity<NetworkDto> createNetwork(@RequestBody NetworkDto networkDto) {
	NetworkDto savedNetworkDto = networkService.createNetwork(networkDto);
	return new ResponseEntity<>(savedNetworkDto, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<NetworkDto> getNetwork(@PathVariable("id") Long networkId) {
	NetworkDto networkDto = networkService.getNetworkById(networkId);
	return ResponseEntity.ok(networkDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<NetworkDto> updateNetwork(@PathVariable("id") Long networkId,
	    @RequestBody NetworkDto updatedNetwork) {
	updatedNetwork.setDateStart(LocalDate.now());
	NetworkDto networkDto = networkService.updateNetwork(networkId, updatedNetwork);
	return ResponseEntity.ok(networkDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteNetwork(@PathVariable("id") Long networkId) {
	networkService.deleteNetwork(networkId);
	return ResponseEntity.ok("The network has been successfully deleted");
    }

    @GetMapping()
    public ResponseEntity<List<NetworkDto>> getAllNetworks() {
	List<NetworkDto> networks = networkService.getAllNetworks();
	return ResponseEntity.ok(networks);
    }

    @GetMapping("/between/{userId1}/{userId2}")
    public ResponseEntity<NetworkDto> getNetworkBetweenUsers(@PathVariable Long userId1, @PathVariable Long userId2) {
	NetworkDto network = networkService.getNetworkBetweenUsers(userId1, userId2);
	return ResponseEntity.ok(network);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<UserDto>> getUserConnections(@PathVariable("id") Long userId) {
	List<UserDto> connections = networkService.getUserConnections(userId);
	return ResponseEntity.ok(connections);
    }

    @GetMapping("/user/{userId1}/mutual/{userId2}")
    public ResponseEntity<List<UserDto>> getUserMutualConnections(@PathVariable Long userId1,
	    @PathVariable Long userId2) {
	List<UserDto> connections = networkService.getUsersMutualConnections(userId1, userId2);
	return ResponseEntity.ok(connections);
    }

    @GetMapping("/user/{userId1}/exclusive/{userId2}")
    public ResponseEntity<List<UserDto>> getUserExclusiveConnections(@PathVariable Long userId1,
	    @PathVariable Long userId2) {
	List<UserDto> connections = networkService.getUsersExclusiveConnections(userId1, userId2);
	return ResponseEntity.ok(connections);
    }

    @GetMapping("/user/{id}/secondary")
    public ResponseEntity<List<UserDto>> getUserSecondaryConnections(@PathVariable("id") Long userId) {
	List<UserDto> connections = networkService.getUserSecondaryConnections(userId);
	return ResponseEntity.ok(connections);
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<List<UserDto>> getGroupConnections(@PathVariable("id") Long groupId) {
	List<UserDto> connections = networkService.getGroupConnections(groupId);
	return ResponseEntity.ok(connections);
    }

}
