package io.github.jthamayo.backend.service.impl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import io.github.jthamayo.backend.dto.NetworkDto;
import io.github.jthamayo.backend.dto.RequestDto;
import io.github.jthamayo.backend.dto.UserDto;
import io.github.jthamayo.backend.dto.UserSummary;
import io.github.jthamayo.backend.entity.Group;
import io.github.jthamayo.backend.entity.Network;
import io.github.jthamayo.backend.entity.Request;
import io.github.jthamayo.backend.entity.User;
import io.github.jthamayo.backend.exception.InvalidOperationException;
import io.github.jthamayo.backend.exception.ResourceNotFoundException;
import io.github.jthamayo.backend.mapper.NetworkMapper;
import io.github.jthamayo.backend.mapper.UserMapper;
import io.github.jthamayo.backend.repository.GroupRepository;
import io.github.jthamayo.backend.repository.NetworkRepository;
import io.github.jthamayo.backend.repository.RequestRepository;
import io.github.jthamayo.backend.repository.UserRepository;
import io.github.jthamayo.backend.service.NetworkService;
import io.github.jthamayo.backend.service.RequestService;

@Service
public class NetworkServiceImpl implements NetworkService {

    private NetworkRepository networkRepository;
    private UserRepository userRepository;
    private GroupRepository groupRepository;
    private RequestRepository requestRepository;
    private RequestService requestService;

    public NetworkServiceImpl(NetworkRepository networkRepository, UserRepository userRepository,
	    GroupRepository groupRepository, RequestRepository requestRepository, RequestService requestService) {
	this.networkRepository = networkRepository;
	this.userRepository = userRepository;
	this.groupRepository = groupRepository;
	this.requestService = requestService;
	this.requestRepository = requestRepository;
    }

    @Override
    public NetworkDto createNetwork(NetworkDto networkDto) {
	if (networkDto.getUserId1().equals(networkDto.getUserId2())) {
	    throw new InvalidOperationException("A user cannot connect with themselves");
	}
	Optional<Network> existingNetwork = networkRepository.findNetworkBetweenUsers(networkDto.getUserId1(),
		networkDto.getUserId2());
	if (existingNetwork.isPresent()) {
	    throw new InvalidOperationException("Users are already connected");
	}
	// TODO verify accepted request
	Optional<Request> acceptedRequest = requestRepository.findAcceptedRequestBetweenUsers(networkDto.getUserId1(),
		networkDto.getUserId2());
	if (!acceptedRequest.isPresent()) {
	    throw new InvalidOperationException("This request hasn't been accepted");
	}
	Network network = NetworkMapper.mapToNetwork(networkDto);
	network.setUser1(userRepository.findById(networkDto.getUserId1())
		.orElseThrow(() -> new ResourceNotFoundException("User not found: " + networkDto.getUserId1())));
	network.setUser2(userRepository.findById(networkDto.getUserId2())
		.orElseThrow(() -> new ResourceNotFoundException("User not found: " + networkDto.getUserId2())));
	network.setDateStart(LocalDate.now());
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
    public List<UserSummary> getUserConnections(Long userId) {
	userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));
	List<User> users = networkRepository.findConnectedUsers(userId);
	return users.stream().map(UserMapper::mapToUserSummary).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getUsersMutualConnections(Long userId1, Long userId2) {
	List<User> intersection = networkRepository.findMutualConnections(userId1, userId2);
	return intersection.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getUsersExclusiveConnections(Long userId1, Long userId2) {
	List<User> relativeComplement = networkRepository.findExclusiveConnections(userId1, userId2);
	return relativeComplement.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getGroupConnections(Long groupId) {
	Group group = groupRepository.findById(groupId)
		.orElseThrow(() -> new ResourceNotFoundException("Group not found: " + groupId));
	List<Long> users = group.getUsers().stream().map(User::getId).collect(Collectors.toList());
	List<User> groupConnections = networkRepository.findConnectionsOutsideGroup(users);
	return groupConnections.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public List<UserSummary> getUserSecondaryConnections(Long userId) {
	userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));
	Set<User> userConnections = new HashSet<>(networkRepository.findConnectedUsers(userId));
	Set<User> secondaryConnections = userConnections.stream()
		.flatMap(friend -> networkRepository.findConnectedUsers(friend.getId()).stream())
		.filter(secondary -> !secondary.getId().equals(userId) && !userConnections.contains(secondary))
		.collect(Collectors.toSet());
	return secondaryConnections.stream().map(UserMapper::mapToUserSummary).collect(Collectors.toList());
    }

    @Override
    public List<UserSummary> getUnconnectedUsers(Long userId) {
	userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));
	List<User> users = networkRepository.findUnconnectedUsers(userId, PageRequest.of(0, 50));
	return users.stream().map(user -> {
	    Optional<RequestDto> requested = requestService.getPendingRequestBetweenUsers(userId, user.getId());

	    UserSummary userDto = UserMapper.mapToUserSummary(user);
	    if (requested.isPresent()) {
		RequestDto request = requested.get();
		if (request.getUserReceiver().equals(userId)) {
		    return null;
		}
		userDto.setHasPendingRequest(true);
	    } else {
		userDto.setHasPendingRequest(false);
	    }
	    return userDto;
	}).filter(Objects::nonNull).collect(Collectors.toList());
    }

}
