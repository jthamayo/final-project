package io.github.jthamayo.backend.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.github.jthamayo.backend.dto.NetworkDto;
import io.github.jthamayo.backend.dto.UserDto;
import io.github.jthamayo.backend.entity.Group;
import io.github.jthamayo.backend.entity.Network;
import io.github.jthamayo.backend.entity.User;
import io.github.jthamayo.backend.exception.ResourceNotFoundException;
import io.github.jthamayo.backend.mapper.NetworkMapper;
import io.github.jthamayo.backend.mapper.UserMapper;
import io.github.jthamayo.backend.repository.GroupRepository;
import io.github.jthamayo.backend.repository.NetworkRepository;
import io.github.jthamayo.backend.repository.UserRepository;
import io.github.jthamayo.backend.service.NetworkService;

@Service
public class NetworkServiceImpl implements NetworkService {

    private NetworkRepository networkRepository;
    private UserRepository userRepository;
    private GroupRepository groupRepository;

    public NetworkServiceImpl(NetworkRepository networkRepository, UserRepository userRepository,
	    GroupRepository groupRepository) {
	this.networkRepository = networkRepository;
	this.userRepository = userRepository;
	this.groupRepository = groupRepository;
    }

    @Override
    public NetworkDto createNetwork(NetworkDto networkDto) {
	Network network = NetworkMapper.mapToNetwork(networkDto);
	network.setUser1(userRepository.findById(networkDto.getUserId1())
		.orElseThrow(() -> new ResourceNotFoundException("User not found: " + networkDto.getUserId1())));
	network.setUser2(userRepository.findById(networkDto.getUserId2())
		.orElseThrow(() -> new ResourceNotFoundException("User not found: " + networkDto.getUserId2())));
	return NetworkMapper.mapToNetworkDto(networkRepository.save(network));
    }

    @Override
    public NetworkDto getNetworkById(Long networkId) {
	Network network = networkRepository.findById(networkId)
		.orElseThrow(() -> new ResourceNotFoundException("Network not found: " + networkId));
	return NetworkMapper.mapToNetworkDto(network);
    }

    @Override
    public NetworkDto updateNetwork(Long networkId, NetworkDto networkDto) {
	Network network = networkRepository.findById(networkId)
		.orElseThrow(() -> new ResourceNotFoundException("Network not found: " + networkId));
	network.setUser1(userRepository.findById(networkDto.getUserId1())
		.orElseThrow(() -> new ResourceNotFoundException("User not found: " + networkDto.getUserId1())));
	network.setUser2(userRepository.findById(networkDto.getUserId2())
		.orElseThrow(() -> new ResourceNotFoundException("User not found: " + networkDto.getUserId2())));
	network.setDateStart(networkDto.getDateStart());
	return NetworkMapper.mapToNetworkDto(networkRepository.save(network));
    }

    @Override
    public void deleteNetwork(Long networkId) {
	networkRepository.findById(networkId)
		.orElseThrow(() -> new ResourceNotFoundException("Network not found: " + networkId));
	networkRepository.deleteById(networkId);
    }

    @Override
    public List<NetworkDto> getAllNetworks() {
	List<Network> networks = networkRepository.findAll();
	return networks.stream().map(NetworkMapper::mapToNetworkDto).collect(Collectors.toList());
    }

    @Override
    public NetworkDto getNetworkBetweenUsers(Long userId1, Long userId2) {
	Network network = networkRepository
		.findNetworkBetweenUsers(Math.min(userId1, userId2), Math.max(userId1, userId2))
		.orElseThrow(() -> new ResourceNotFoundException(
			"Network not found between " + userId1 + " and " + userId2));
	return NetworkMapper.mapToNetworkDto(network);
    }

    @Override
    public List<UserDto> getUserConnections(Long userId) {
	List<User> users = networkRepository.findConnectedUsers(userId);
	return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getUsersMutualConnections(Long userId1, Long userId2) {
	List<User> user1Connections = networkRepository.findConnectedUsers(userId1);
	List<User> user2Connections = networkRepository.findConnectedUsers(userId2);
	Set<User> intersection = new HashSet<>(user1Connections);
	intersection.retainAll(user2Connections);
	return intersection.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getUsersExclusiveConnections(Long userId1, Long userId2) {
	List<User> user1Connections = networkRepository.findConnectedUsers(userId1);
	List<User> user2Connections = networkRepository.findConnectedUsers(userId2);
	Set<User> relativeComplement = new HashSet<>(user2Connections);
	relativeComplement.removeAll(user1Connections);
	return relativeComplement.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getUserSecondaryConnections(Long userId) {
	userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));
	Set<User> userConnections = new HashSet<>(networkRepository.findConnectedUsers(userId));
	Set<User> relativeComplement = new HashSet<>();
	for (User connection : userConnections) {
	    List<User> secondaryConnections = networkRepository.findConnectedUsers(connection.getId());
	    for (User sc : secondaryConnections) {
		if (!sc.getId().equals(userId) && !userConnections.contains(sc)) {
		    relativeComplement.add(sc);
		}
	    }
	}
	return relativeComplement.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getGroupConnections(Long groupId) {
	Group group = groupRepository.findById(groupId)
		.orElseThrow(() -> new ResourceNotFoundException("Group not found: " + groupId));
	Set<User> groupConnections = new HashSet<>(
		networkRepository.findConnectedUsers(group.getUsers().get(0).getId()));
	for (User user : group.getUsers()) {
	    groupConnections.addAll(networkRepository.findConnectedUsers(user.getId()));
	}
	groupConnections.removeAll(group.getUsers());
	return groupConnections.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getGroupMutualConnections(Long groupId) {
	Group group = groupRepository.findById(groupId)
		.orElseThrow(() -> new ResourceNotFoundException("Group not found: " + groupId));
	Set<User> mutuals = new HashSet<>(networkRepository.findConnectedUsers(group.getUsers().get(0).getId()));
	for (int i = 1; i < group.getUsers().size(); i++) {
	    mutuals.retainAll(networkRepository.findConnectedUsers(group.getUsers().get(i).getId()));
	}
	mutuals.removeAll(group.getUsers());
	return mutuals.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }
}
